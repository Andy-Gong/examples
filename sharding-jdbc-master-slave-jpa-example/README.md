# Sharding JDBC master slave model with Spring Data JPA
Sharding JDBC master-slave model decouples the write and read automatically. It implements DataSource, Connection, PreparedStatement interfaces.

![image](https://github.com/Andy-Gong/examples/blob/master/sharding-jdbc-master-slave-jpa-example/sharding-jdbc-master-slave.png)

MasterSlaveDataSource.class, which includes a master data source and multiple slave data sources. MasterSlaveRule defines the rule of database and tables, which includes the master database name and the slave database names.

```java
public class MasterSlaveDataSource extends AbstractDataSourceAdapter {
    
    private final DatabaseMetaData databaseMetaData;
    
    private final MasterSlaveRule masterSlaveRule;
    
    private final ShardingProperties shardingProperties;
    
    public MasterSlaveDataSource(final Map<String, DataSource> dataSourceMap, final MasterSlaveRuleConfiguration masterSlaveRuleConfig,
                                 final Map<String, Object> configMap, final Properties props) throws SQLException {
        super(dataSourceMap);
        databaseMetaData = getDatabaseMetaData(dataSourceMap);
        if (!configMap.isEmpty()) {
            ConfigMapContext.getInstance().getConfigMap().putAll(configMap);
        }
        this.masterSlaveRule = new MasterSlaveRule(masterSlaveRuleConfig);
        shardingProperties = new ShardingProperties(null == props ? new Properties() : props);
    }
    
    @Override
    public final MasterSlaveConnection getConnection() {
        return new MasterSlaveConnection(this, getShardingTransactionalDataSources().getDataSourceMap(), TransactionTypeHolder.get());
    }
}
``` 

MasterSlaveConnection.class implements Connection, it supports master-slave model.
```java
public final class MasterSlaveConnection extends AbstractConnectionAdapter {
    
    private final MasterSlaveDataSource masterSlaveDataSource;
    
    private final Map<String, DataSource> dataSourceMap;
    
    public MasterSlaveConnection(final MasterSlaveDataSource masterSlaveDataSource, final Map<String, DataSource> dataSourceMap, final TransactionType transactionType) {
        super(transactionType);
        this.masterSlaveDataSource = masterSlaveDataSource;
        this.dataSourceMap = dataSourceMap;
    }
  
    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return new MasterSlavePreparedStatement(this, sql, resultSetType, resultSetConcurrency);
    }
}
```

MasterSlavePreparedStatement.class implements PreparedStatement and supports master-slave. During create MasterSlavePreparedStatement, we will create a physical HikariProxyPreparedStatement according to the SQL (if the SQLType is DQL, it is from slave datasource, elas it is from master datasource).
MasterSlaveRouter.class is responsible to route sql to find the datasource name(master name or slave name)
```java
public final class MasterSlavePreparedStatement extends AbstractMasterSlavePreparedStatementAdapter {
    
    private final MasterSlaveConnection connection;
    
    @Getter(AccessLevel.NONE)
    private final MasterSlaveRouter masterSlaveRouter;
    
    private final Collection<PreparedStatement> routedStatements = new LinkedList<>();
    
    public MasterSlavePreparedStatement(
            final MasterSlaveConnection connection, final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        this.connection = connection;
        masterSlaveRouter = new MasterSlaveRouter(connection.getMasterSlaveDataSource().getMasterSlaveRule(),
                connection.getMasterSlaveDataSource().getShardingProperties().<Boolean>getValue(ShardingPropertiesConstant.SQL_SHOW));
        for (String each : masterSlaveRouter.route(sql)) {
            PreparedStatement preparedStatement = connection.getConnection(each).prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
            routedStatements.add(preparedStatement);
        }
    }
   
    @Override
    public ResultSet executeQuery() throws SQLException {
        Preconditions.checkArgument(1 == routedStatements.size(), "Cannot support executeQuery for DDL");
        return routedStatements.iterator().next().executeQuery();
    }
    
    @Override
    public int executeUpdate() throws SQLException {
        int result = 0;
        for (PreparedStatement each : routedStatements) {
            result += each.executeUpdate();
        }
        return result;
    }
}
```
# Example 
## db scripts
create_master.sql creates master database and tables. 

create_slave.sql creates slave database and tables.
## application.properties
database configuration properties, one master database and one slave database.
```properties
#master
master.url=jdbc:mysql://localhost:3306/master?characterEncoding=utf8&useSSL=false
master.username=root
master.password=
master.driverClassName=com.mysql.jdbc.Driver
master.databaseName=master

#slave
slave.url=jdbc:mysql://localhost:3306/slave?characterEncoding=utf8&useSSL=false
slave.username=root
slave.password=
slave.driverClassName=com.mysql.jdbc.Driver
slave.databaseName=slave
```
## postman scripts
create_user, it will create the user into the master database.

query_user, it will query the user from the slave database.
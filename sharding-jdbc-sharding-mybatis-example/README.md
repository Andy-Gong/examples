# Sharding JDBC sharding database and tables with mybatis
Sharding JDBC supports to shard the database and tables according sharding rules which can be defined at yaml file. Sharding JDBC rewrites the DataSource, Connection, PreparedStatement interfaces. And class-structure as below.

![image](https://github.com/Andy-Gong/examples/blob/master/sharding-jdbc-sharding-mybatis-example/sharding-class-structure.jpg)

## Source Code analyze
The sharding process as below image.
![image](https://github.com/Andy-Gong/examples/blob/master/sharding-jdbc-sharding-mybatis-example/sharding-workflow.jpg)

ShardingDataSourceFactory: generating the ShardingDataSource according to yaml file.

```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShardingDataSourceFactory {
    
    /**
     * Create sharding data source.
     *
     * @param dataSourceMap data source map
     * @param shardingRuleConfig rule configuration for databases and tables sharding
     * @param configMap config map
     * @param props properties for data source
     * @return sharding data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(
            final Map<String, DataSource> dataSourceMap, final ShardingRuleConfiguration shardingRuleConfig, final Map<String, Object> configMap, final Properties props) throws SQLException {
        return new ShardingDataSource(dataSourceMap, new ShardingRule(shardingRuleConfig, dataSourceMap.keySet()), configMap, props);
    }
}
```

ShardingDataSource defines all sharding rule，and ShardingContext includes the ShardingRules, execute engine，route info which will be used when creating the ShardingConnection.
ShardingDataSource includes all data sources in field: Map<String,DataSource>.
```java
public class ShardingDataSource extends AbstractDataSourceAdapter {
    
    private final ShardingContext shardingContext;
    @Getter
    private final Map<String, DataSource> originalDataSourceMap;
    private final Map<TransactionType, Map<String, DataSource>> transactionalDataSourceMap;    
    public ShardingDataSource(final Map<String, DataSource> dataSourceMap, final ShardingRule shardingRule, final Map<String, Object> configMap, final Properties props) throws SQLException {
        super(dataSourceMap);
        checkDataSourceType(dataSourceMap);
        if (!configMap.isEmpty()) {
            ConfigMapContext.getInstance().getConfigMap().putAll(configMap);
        }
        shardingContext = new ShardingContext(getDataSourceMap(), shardingRule, getDatabaseType(), props);
    }
   
    @Override
    public final ShardingConnection getConnection() {
        return new ShardingConnection(getShardingTransactionalDataSources().getDataSourceMap(), shardingContext, TransactionTypeHolder.get());
    }
    }
```

ShardingConnection supports sharding database and tables connection, and it creates ShardingPreparedStatement. It is not a real connection, but it caches a lot of connections, if the cache can't has the connection of the table, it will create a new one and put it into the cache.

```java
@Getter
public final class ShardingConnection extends AbstractConnectionAdapter {
    
    private final Map<String, DataSource> dataSourceMap;
    private final Multimap<String, Connection> cachedConnections = LinkedHashMultimap.create();

    private final ShardingContext shardingContext;
    
    public ShardingConnection(final Map<String, DataSource> dataSourceMap, final ShardingContext shardingContext, final TransactionType transactionType) {
        super(transactionType);
        this.dataSourceMap = dataSourceMap;
        this.shardingContext = shardingContext;
    }
   
    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency) {
        return new ShardingPreparedStatement(this, sql, resultSetType, resultSetConcurrency);
    }
}
```

ShardingPreparedStatement is the key class of Sharding JDBC, it has 3 steps：
1. SQL route, generate the SQLRouteResult

Step 1：
OptimizeEngine：根据各个table的sharding rule生成sharding conditions，每个sharding condition包含多个ListShardingValue，其包含三个字段：logicTableName（逻辑表名），columnName（sharding 列名），values（sharding列对应的值）。
Step 2：
RoutingEngine：根据sharding conditions生成RouteResult，它包含tableUnits，每个tableUnit包含DataSourceName，routingTables：logicTableName and actualTableName。
Step 3：
SQLRewriteEngine：rewrite logic table name to actual table name。生成最终的routeUnit，包含DataSourceName，actual SQL

2. generate PreParedStatements, according to SQLRouteResult generates Collection<ShardingExecuteGroup<StatementExecuteUnit>> executeGroups.
3. execute PreparedStatements, PreparedStatementExecutor.execute()
```java
public final class ShardingPreparedStatement extends AbstractShardingPreparedStatementAdapter {
    
    @Getter
    private final ShardingConnection connection;
    
    private final PreparedStatementRoutingEngine routingEngine;
    
    private final PreparedStatementExecutor preparedStatementExecutor;
    
    private final BatchPreparedStatementExecutor batchPreparedStatementExecutor;
    
    private SQLRouteResult routeResult;
    
    private ResultSet currentResultSet;
    
    private ShardingPreparedStatement(
            final ShardingConnection connection, final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability, final boolean returnGeneratedKeys) {
        this.connection = connection;
        ShardingContext shardingContext = connection.getShardingContext();
        routingEngine = new PreparedStatementRoutingEngine(sql, shardingContext.getShardingRule(), shardingContext.getMetaData(), shardingContext.getDatabaseType(), 
                shardingContext.getShardingProperties().<Boolean>getValue(ShardingPropertiesConstant.SQL_SHOW));
        preparedStatementExecutor = new PreparedStatementExecutor(resultSetType, resultSetConcurrency, resultSetHoldability, returnGeneratedKeys, connection);
        batchPreparedStatementExecutor = new BatchPreparedStatementExecutor(resultSetType, resultSetConcurrency, resultSetHoldability, returnGeneratedKeys, connection);
    }
    
    @Override
    public boolean execute() throws SQLException {
        try {
            clearPrevious();
            sqlRoute();
            initPreparedStatementExecutor();
            return preparedStatementExecutor.execute();
        } finally {
            refreshTableMetaData(connection.getShardingContext(), routeResult.getSqlStatement());
            clearBatch();
        }
    }
    }
```


# Example 
## db scripts
ds0.sql creates ds0 database and user/order tables. 

ds1.sql creates ds1 database and user/order tables.

## application.yml
here we defines tow databases: ds0 and ds1, and tow tables: user and order. 
Database is sharding by tenant id, table is sharding by id:
1. when id is odd, storing data into table_1,
2. when id is even, storing data into table_0
```yaml
sharding:
  jdbc:
    datasource:
      names: ds0,ds1
      ds0:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.jdbc.Driver
        jdbcUrl: jdbc:mysql://localhost:3306/ds0
        username: root
        password:
      ds1:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.jdbc.Driver
        jdbcUrl: jdbc:mysql://localhost:3306/ds1
        username: root
        password:
    config:
      sharding:
        props:
          sql.show: true
        tables:
          user:
            key-generator-column-name: user_id
            actual-data-nodes: ds${0..1}.user_${0..1}
            database-strategy:
              standard:
                precise-algorithm-class-name: sharding.jdbc.example.shardingstrategy.TenantIdSharding
                sharding-column: tenant_id
            table-strategy:
                inline:
                  shardingColumn: user_id
                  algorithm-expression: user_${user_id % 2}
          order:
            key-generator-column-name: order_id
            actual-data-nodes: ds${0..1}.order_${0..1}
            database-strategy:
              standard:
                precise-algorithm-class-name: sharding.jdbc.example.shardingstrategy.TenantIdSharding
                sharding-column: tenant_id
            table-strategy:
              inline:
                shardingColumn: order_id
                algorithm-expression: order_${order_id % 2}
```
## DatabaseShardingTest
Database sharding test cases, the database is sharded by tenant id.
1. If the tenant id is 0, the database is ds0.
2. If the tenant id is 1, the database is ds1.

## TableShardingTest
Table sharding test cases. And the table is sharded by the strategy that the table name is table_id%2.
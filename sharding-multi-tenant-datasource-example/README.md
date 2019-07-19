# Sharding database by tenant
In this example, we have a global database to store the metadata of database of each tenant, which is defined in table: global.tenant_meta. And each tenant has a dedicate database, whose name likes 'schema_2', '2' is tenant id.

In REST API request, we will doFilter to build a process context which includes the tenant basic info and the data source url of this tenant. And in each request, it MUST include the header: "tenantId". You can see the detail implementation in class ContextFilter.

## DataSource design
There are two DataSources: GlobalDataSource and MultiTenancyDataSource. 

GlobalDataSource is used to access global database, which can get sharding info of tenants.
    
```java
public class GlobalDataSourceConfigure {

    @Bean(name = "globalDataSource")
    public DataSource globalDataSourceConfigure() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.getDriverClassName());
        dataSource.setUsername(this.getUsername());
        dataSource.setPassword(this.getPassword());
        dataSource.setUrl(this.getUrl());
        dataSource.setMaxTotal(this.getMaxTotal());
        dataSource.setMaxIdle(this.getMaxIdle());
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }
}
```

MultiTenancyDataSource is used to access each tenant database, which stores all data of this tenant.
```java
public class MultiTenancyDataSource extends AbstractDataSource {

    /**
     * key: tenantId, value: DataSource
     */
    private static Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    /**
     * <p>Attempts to establish a connection with the data source that
     * this {@code DataSource} object represents.
     *
     * @return a connection to the data source
     * @throws SQLException if a database access error occurs
     * @throws SQLTimeoutException when the driver has determined that the timeout value specified by the {@code setLoginTimeout} method has been exceeded and
     * has at least tried to cancel the current database connection attempt
     */
    @Override
    public Connection getConnection() throws SQLException {
        Context context = ContextManager.getContext();
        if (context == null) {
            return getBasicDataSource().getConnection();
        } else {
            DataSource dataSource = dataSourceMap.get(String.valueOf(context.getTenant().getUrl()));
            if (dataSource != null) {
                return dataSource.getConnection();
            } else {
                dataSource = getDataSource(context.getTenant());
                dataSourceMap.put(String.valueOf(context.getTenant().getUrl()), dataSource);
                return dataSource.getConnection();
            }
        }
    }

    private DataSource getDataSource(Tenant tenant) {
        BasicDataSource dataSource = getBasicDataSource();
        dataSource.setUrl(tenant.getUrl());
        return dataSource;
    }

    private BasicDataSource getBasicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(configure.getDriverClassName());
        dataSource.setUsername(configure.getUsername());
        dataSource.setPassword(configure.getPassword());
        dataSource.setUrl(configure.getUrl());
        dataSource.setMaxTotal(configure.getMaxTotal());
        dataSource.setMaxIdle(configure.getMaxIdle());
        dataSource.setValidationQuery(configure.getValidation());
        return dataSource;
    }
}
```

## Test description

global_database_tables.sql creates global database and tables.
sharding_database_tables.sql creates tenant:2 database and tables.

post_tenant script can create a sharding record for tenant:2.
post_user script can create a user record for tenant:2.

At last, the user record will save to different database according to the value of request header:"tenantId".

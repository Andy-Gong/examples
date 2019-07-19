# Multi-tenancy support via hibernate SPI
In this example, it implements hibernate SPIs CurrentTenantIdentifierResolver and MultiTenantConnectionProvider.

CurrentTenantIdentifierResolver is a callback registered with the {@link org.hibernate.SessionFactory} that is responsible for resolving the current tenant identifier for use with {@link CurrentSessionContext} and {@link org.hibernate.SessionFactory#getCurrentSession()}
```java
public class CustomTenantIdentifier implements CurrentTenantIdentifierResolver {

    /**
     * Resolve the current tenant identifier.
     *
     * @return The current tenant identifier
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
        Context context = ContextManager.getContext();
        if (context == null) {
            return "1";
        }
        return String.valueOf(context.getTenantId());
    }
}
```
MultiTenantConnectionProvider: a specialized Connection provider contract used when the application is using multi-tenancy support requiring tenant aware connections.

```java
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

    public static final String SCHEMA_PREFIX = "schema_";
    @Autowired
    private DataSource dataSource;

    /**
     * Allows access to the database metadata of the underlying database(s) in situations where we do not have a
     * tenant id (like startup processing, for example).
     *
     * @return The database metadata.
     * @throws SQLException Indicates a problem opening a connection
     */
    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Obtains a connection for Hibernate use according to the underlying strategy of this provider.
     *
     * @param tenantIdentifier The identifier of the tenant for which to get a connection
     * @return The obtained JDBC connection
     * @throws SQLException Indicates a problem opening a connection
     * @throws HibernateException Indicates a problem otherwise obtaining a connection.
     */
    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setCatalog(SCHEMA_PREFIX + tenantIdentifier);
        return connection;
    }
}
```
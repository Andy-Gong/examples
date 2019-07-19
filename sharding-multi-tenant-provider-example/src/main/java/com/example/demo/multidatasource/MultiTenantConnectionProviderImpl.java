package com.example.demo.multidatasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
     * Release a connection obtained from {@link #getAnyConnection}
     *
     * @param connection The JDBC connection to release
     * @throws SQLException Indicates a problem closing the connection
     */
    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
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

    /**
     * Release a connection from Hibernate use.
     *
     * @param tenantIdentifier The identifier of the tenant.
     * @param connection The JDBC connection to release
     * @throws SQLException Indicates a problem closing the connection
     * @throws HibernateException Indicates a problem otherwise releasing a connection.
     */
    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Does this connection provider support aggressive release of JDBC
     * connections and re-acquisition of those connections (if need be) later?
     * <p/>
     * This is used in conjunction with {@link Environment#RELEASE_CONNECTIONS}
     * to aggressively release JDBC connections.  However, the configured ConnectionProvider
     * must support re-acquisition of the same underlying connection for that semantic to work.
     * <p/>
     * Typically, this is only true in managed environments where a container
     * tracks connections by transaction or thread.
     *
     * Note that JTA semantic depends on the fact that the underlying connection provider does
     * support aggressive release.
     *
     * @return {@code true} if aggressive releasing is supported; {@code false} otherwise.
     */
    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    /**
     * Can this wrapped service be unwrapped as the indicated type?
     *
     * @param unwrapType The type to check.
     * @return True/false.
     */
    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    /**
     * Unproxy the service proxy
     *
     * @param unwrapType The java type as which to unwrap this instance.
     * @return The unwrapped reference
     * @throws UnknownUnwrapTypeException if the servicebe unwrapped as the indicated type
     */
    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}

package sharding.multitenancy.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.stereotype.Component;
import sharding.multitenancy.context.Context;
import sharding.multitenancy.context.ContextManager;
import sharding.multitenancy.model.global.Tenant;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;


@Component("dataSource")
public class MultiTenancyDatasource extends AbstractDataSource {

    /**
     * key: tenantId, value: DataSource
     */
    private static Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    @Autowired
    private DataSourceConfigure configure;


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
            DataSource dataSource = dataSourceMap.get(String.valueOf(context.getTenant().getId()));
            if (dataSource != null) {
                return dataSource.getConnection();
            } else {
                dataSource = getDataSource(context.getTenant());
                dataSourceMap.put(String.valueOf(context.getTenant().getId()), dataSource);
                return dataSource.getConnection();
            }
        }
    }

    /**
     * <p>Attempts to establish a connection with the data source that
     * this {@code DataSource} object represents.
     *
     * @param username the database user on whose behalf the connection is being made
     * @param password the user's password
     * @return a connection to the data source
     * @throws SQLException if a database access error occurs
     * @throws SQLTimeoutException when the driver has determined that the timeout value specified by the {@code setLoginTimeout} method has been exceeded and
     * has at least tried to cancel the current database connection attempt
     * @since 1.4
     */
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }

    private DataSource getDataSource(Tenant tenant) {
        BasicDataSource dataSource = getBasicDataSource();
        dataSource.setUrl(dataSource.getUrl() + tenant.getSchema());
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
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }
}

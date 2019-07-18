package sharding.jdbc.example.datasource;

public interface DataSourceConfig {
    String getUrl();
    String getUsername();
    String getPassword();
    String getDriverClassName();
    String getDatabaseName();
}

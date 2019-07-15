package sharding.jdbc.example.config;

public interface DataSourceConfig {
    String getUrl();
    String getUsername();
    String getPassword();
    String getDriverClassName();
    String getDatabaseName();

}

package sharding.jdbc.example.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "master")
@Getter
@Setter
public class MasterConfig implements DataSourceConfig{

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String databaseName;
}

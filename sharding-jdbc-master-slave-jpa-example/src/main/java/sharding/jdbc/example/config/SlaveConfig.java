package sharding.jdbc.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "slave")
@Getter
@Setter
public class SlaveConfig implements DataSourceConfig{


    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String databaseName;
}

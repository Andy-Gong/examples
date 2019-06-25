//package sharding.multitenancy.datasource;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//@ConfigurationProperties(prefix = "globalDatasource")
//public class GlobalDataSourceConfigure {
//
//    private String driverClassName;
//    private String url;
//    private String username;
//    private String password;
//    private int maxTotal = 100;
//    private int maxIdle = 20;
//
//    @Bean
//    public DataSource globalDataSourceConfigure() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(this.getDriverClassName());
//        dataSource.setUsername(this.getUsername());
//        dataSource.setPassword(this.getPassword());
//        dataSource.setUrl(this.getUrl());
//        dataSource.setMaxTotal(this.getMaxTotal());
//        dataSource.setMaxIdle(this.getMaxIdle());
//        dataSource.setValidationQuery("SELECT 1");
//        return dataSource;
//    }
//
//    public int getMaxTotal() {
//        return maxTotal;
//    }
//
//    public void setMaxTotal(int maxTotal) {
//        this.maxTotal = maxTotal;
//    }
//
//    public int getMaxIdle() {
//        return maxIdle;
//    }
//
//    public void setMaxIdle(int maxIdle) {
//        this.maxIdle = maxIdle;
//    }
//
//    public String getDriverClassName() {
//        return driverClassName;
//    }
//
//    public void setDriverClassName(String driverClassName) {
//        this.driverClassName = driverClassName;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}

package sharding.multitenancy.datasource;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * this global data source is used to access global database which has sharding meta data.
 */
@Configuration
@ConfigurationProperties(prefix = "global-data-source")
@EnableJpaRepositories(
    entityManagerFactoryRef = "globalEntityManagerFactory",
    transactionManagerRef = "globalTransactionManager",
    basePackages = "sharding.multitenancy.repository.global"
)
@EnableTransactionManagement
public class GlobalDataSourceConfigure {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int maxTotal = 100;
    private int maxIdle = 20;

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

    @Primary
    @Bean(name = "globalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean globalEntityManagerFactory(
        final @Qualifier("globalDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("sharding.multitenancy.model.global");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManagerFactoryBean;
    }

    @Bean(name = "globalTransactionManager")
    public PlatformTransactionManager globalTransactionManager(@Qualifier("globalEntityManagerFactory")
        EntityManagerFactory firstEntityManagerFactory) {
        return new JpaTransactionManager(firstEntityManagerFactory);
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
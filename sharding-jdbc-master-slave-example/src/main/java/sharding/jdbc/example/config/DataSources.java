package sharding.jdbc.example.config;

import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

@Component
public class DataSources {

    @Bean
    public DataSource getDataSource(MasterConfig masterConfig, SlaveConfig slaveConfig) {
        DataSource master = buidDataSource(masterConfig);
        DataSource slave = buidDataSource(slaveConfig);
        Map<String, DataSource> slaveDataSourceMap = new HashMap<>();
        slaveDataSourceMap.put(slaveConfig.getDatabaseName(), slave);
        return MasterSlaveDataSourceFactory.createDataSource(master, slaveDataSourceMap, MasterSlaveLoadBalanceStrategyType.getDefaultStrategyType());
    }

    public DataSource buidDataSource(DataSourceConfig dataSourceConfig) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(dataSourceConfig.getDriverClassName());
        result.setUrl(dataSourceConfig.getUrl());
        result.setUsername(dataSourceConfig.getUsername());
        result.setPassword(dataSourceConfig.getPassword());
        return result;
    }

}

package sharding.jdbc.example.datasource;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.algorithm.masterslave.RandomMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

@Component
public class DataSources {

    @Bean
    public DataSource getDataSource(MasterConfig masterConfig, SlaveConfig slaveConfig) throws SQLException {
        DataSource master = buidDataSource(masterConfig);
        DataSource slave = buidDataSource(slaveConfig);
        Map<String, DataSource> slaveDataSourceMap = new HashMap<>();
        slaveDataSourceMap.put(slaveConfig.getDatabaseName(), slave);
        slaveDataSourceMap.put(masterConfig.getDatabaseName(), master);
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("master_slave", masterConfig.getDatabaseName(),
                Arrays.asList(slaveConfig.getDatabaseName()), new RandomMasterSlaveLoadBalanceAlgorithm());
        return MasterSlaveDataSourceFactory.createDataSource(slaveDataSourceMap, masterSlaveRuleConfig, new HashMap<>(), new Properties());
    }

    public DataSource buidDataSource(DataSourceConfig dataSourceConfig) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(dataSourceConfig.getDriverClassName());
        result.setJdbcUrl(dataSourceConfig.getUrl());
        result.setUsername(dataSourceConfig.getUsername());
        result.setPassword(dataSourceConfig.getPassword());
        return result;
    }

}

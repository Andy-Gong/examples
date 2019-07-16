package sharding.jdbc.example.shardingstrategy;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

public class TenantIdSharding implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        return collection
                .stream()
                .filter(dataSource -> dataSource.equals("ds" + preciseShardingValue.getValue()))
                .findFirst()
                .get();
    }
}

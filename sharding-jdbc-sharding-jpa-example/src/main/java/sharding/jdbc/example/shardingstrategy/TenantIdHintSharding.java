package sharding.jdbc.example.shardingstrategy;

import io.shardingsphere.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.hint.HintShardingAlgorithm;

import java.util.Arrays;
import java.util.Collection;

public class TenantIdHintSharding implements HintShardingAlgorithm {


    /**
     * Sharding.
     *
     * <p>sharding value injected by hint, not in SQL.</p>
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue sharding value
     * @return sharding result for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ShardingValue shardingValue) {
        ListShardingValue<Integer> listShardingValue = (ListShardingValue) shardingValue;
        Integer tenantId = Integer.valueOf(listShardingValue.getValues().toArray()[0].toString());
        int index = tenantId % availableTargetNames.size();
        return Arrays.asList("ds" + index);
    }
}

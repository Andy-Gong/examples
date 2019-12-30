# Sharding JDBC sharding example with Hint algorithm

Sharding jdbc manages the hint sharding field and value via thread local. And HintManager adds the sharding value. Hint mainly used to below scenes:
1. the sharding value doesn't include in table column, SQL,
2. Operate on the master database forced.

## Source Code analyze

To force sharding with custom sharding value, there are two thing you need to do:
1. Implement HintShardingAlgorithm with deciding datasource sharding and table sharding,
2. In the API access, Adding ShardingValue into HintManager.

### sharding rule configuration

```yaml
sharding:
        props:
          sql.show: true
        tables:
          user:
            actual-data-nodes: ds${0..1}.user_${0..1}
            database-strategy:
              hint:
                algorithmClassName: sharding.jdbc.example.shardingstrategy.TenantIdHintSharding
            table-strategy:
              hint:
                algorithmClassName: sharding.jdbc.example.shardingstrategy.TenantIdHintTableSharding
```

### TenantIdHintSharding and TenantIdHintTableSharding implement HintShardingAlgorithm

```java
public class TenantIdHintSharding implements HintShardingAlgorithm {


    /**
     * Sharding.
     *
     * <p>sharding value injected by hint, not in SQL.</p>
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue sharding value, include logic table name, sharding column, and sharding value.
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
```
### Add ShardingValue in ContextFilter 

```java
@Component
public class ContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HintManager hintManager = HintManager.getInstance();
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String tenantId = httpServletRequest.getHeader("tenantId");
            hintManager.addDatabaseShardingValue("user", tenantId);
            hintManager.addTableShardingValue("user", tenantId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            hintManager.close();
        }
    }
}
```

## db scripts
ds0.sql creates ds0 database and user tables. 

ds1.sql creates ds1 database and user tables.

## application.yml
here we defines tow databases: ds0 and ds1, and tow tables: user and order. 
Database and table are sharding by tenant id.

## PostMan scripts
create_user to create user object, and http header contains tenant id,
update_user to update via user id, and http header contains tenant id
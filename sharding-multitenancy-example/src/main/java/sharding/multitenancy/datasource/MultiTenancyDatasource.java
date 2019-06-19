package sharding.multitenancy.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import sharding.multitenancy.context.ContextManager;

public class MultiTenancyDatasource extends AbstractRoutingDataSource {

    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return ContextManager.getContext().getTenant().getId();
    }
}

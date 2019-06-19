package sharding.multitenancy.context;

import lombok.Builder;
import sharding.multitenancy.model.Tenant;

@Builder
public class Context {

    private Tenant tenant;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}

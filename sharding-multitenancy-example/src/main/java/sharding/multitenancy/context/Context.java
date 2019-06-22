package sharding.multitenancy.context;

import lombok.Builder;
import sharding.multitenancy.model.Tenant;

@Builder
public class Context {

    private Tenant tenant;
    private boolean global = Boolean.FALSE;

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}

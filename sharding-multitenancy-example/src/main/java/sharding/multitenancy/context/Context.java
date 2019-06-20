package sharding.multitenancy.context;

import lombok.Builder;
import sharding.multitenancy.model.Tenant;

@Builder
public class Context {

    private Tenant tenant;
    private boolean isGlobal = Boolean.FALSE;

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}

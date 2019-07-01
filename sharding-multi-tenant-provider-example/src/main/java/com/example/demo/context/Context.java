package com.example.demo.context;

import lombok.Builder;

@Builder
public class Context {

    private long tenantId;

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }
}

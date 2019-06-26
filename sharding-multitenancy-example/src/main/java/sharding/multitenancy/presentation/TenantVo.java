package sharding.multitenancy.presentation;

public class TenantVo {

    private Long id;
    private Long tenantId;
    private String name;
    private String schema;

    public TenantVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {

        private Long id;
        private String name;
        private Long tenantId;
        private String schema;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder tenantId(Long tenantId){
            this.tenantId = tenantId;
            return this;
        }

        public Builder schema(String schema){
            this.schema = schema;
            return this;
        }
        public TenantVo build() {
            TenantVo tenantVo = new TenantVo();
            tenantVo.setId(this.id);
            tenantVo.setName(this.name);
            tenantVo.setTenantId(this.tenantId);
            tenantVo.setSchema(this.schema);
            return tenantVo;
        }
    }
}

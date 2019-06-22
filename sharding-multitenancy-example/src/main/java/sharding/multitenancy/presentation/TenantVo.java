package sharding.multitenancy.presentation;

public class TenantVo {

    private Long id;
    private String name;

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

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {

        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public TenantVo build() {
            TenantVo tenantVo = new TenantVo();
            tenantVo.setId(this.id);
            tenantVo.setName(this.name);
            return tenantVo;
        }
    }
}

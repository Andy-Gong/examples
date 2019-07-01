package sharding.multitenancy.presentation;

public class UserVo {

    private Long id;
    private String name;
    private String region;

    public UserVo() {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String name;
        private String region;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public UserVo build() {
            UserVo userVo = new UserVo();
            userVo.setId(this.id);
            userVo.setName(this.name);
            userVo.setRegion(this.region);
            return userVo;
        }
    }
}

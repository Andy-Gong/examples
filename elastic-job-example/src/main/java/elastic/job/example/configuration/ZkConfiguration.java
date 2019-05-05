package elastic.job.example.configuration;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZkConfiguration {
    private String serverLists;
    private String namespace;
    private int baseSleepTimeMilliseconds;
    private int maxSleepTimeMilliseconds;
    private int maxRetries;
    private int sessionTimeoutMilliseconds;
    private int connectionTimeoutMilliseconds;
    private String digest;
    private ZookeeperRegistryCenter registryCenter;
    private Boolean init = false;


    public ZookeeperRegistryCenter getRegistryCenter() {
        synchronized (init) {
            if (registryCenter == null) {
                registryCenter = new ZookeeperRegistryCenter(this.getZookeeperConfiguration());
                registryCenter.init();
                return this.registryCenter;
            } else {
                return this.registryCenter;
            }
        }
    }

    private ZookeeperConfiguration getZookeeperConfiguration() {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(this.serverLists, this.namespace);
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(this.baseSleepTimeMilliseconds);
        zookeeperConfiguration.setConnectionTimeoutMilliseconds(this.connectionTimeoutMilliseconds);
        zookeeperConfiguration.setDigest(this.digest);
        zookeeperConfiguration.setMaxRetries(this.maxRetries);
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(this.maxSleepTimeMilliseconds);
        zookeeperConfiguration.setSessionTimeoutMilliseconds(this.sessionTimeoutMilliseconds);
        return zookeeperConfiguration;
    }

    public int getBaseSleepTimeMilliseconds() {
        return baseSleepTimeMilliseconds;
    }

    public void setBaseSleepTimeMilliseconds(int baseSleepTimeMilliseconds) {
        this.baseSleepTimeMilliseconds = baseSleepTimeMilliseconds;
    }

    public int getMaxSleepTimeMilliseconds() {
        return maxSleepTimeMilliseconds;
    }

    public void setMaxSleepTimeMilliseconds(int maxSleepTimeMilliseconds) {
        this.maxSleepTimeMilliseconds = maxSleepTimeMilliseconds;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getSessionTimeoutMilliseconds() {
        return sessionTimeoutMilliseconds;
    }

    public void setSessionTimeoutMilliseconds(int sessionTimeoutMilliseconds) {
        this.sessionTimeoutMilliseconds = sessionTimeoutMilliseconds;
    }

    public int getConnectionTimeoutMilliseconds() {
        return connectionTimeoutMilliseconds;
    }

    public void setConnectionTimeoutMilliseconds(int connectionTimeoutMilliseconds) {
        this.connectionTimeoutMilliseconds = connectionTimeoutMilliseconds;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getServerLists() {
        return serverLists;
    }

    public void setServerLists(String serverLists) {
        this.serverLists = serverLists;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}

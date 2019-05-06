package elastic.job.example.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elastic-jobs")
public class ElasticJobsConfiguration {

    public List<ElasticJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<ElasticJob> jobs) {
        this.jobs = jobs;
    }

    private List<ElasticJob> jobs;


    public static class ElasticJob {

        private String jobName;
        private String jobClass;
        private String shardingItemParameters;
        private String cron;
        private int shardingTotalCount;

        public String getJobClass() {
            return jobClass;
        }

        public void setJobClass(String jobClass) {
            this.jobClass = jobClass;
        }

        public String getShardingItemParameters() {
            return shardingItemParameters;
        }

        public void setShardingItemParameters(String shardingItemParameters) {
            this.shardingItemParameters = shardingItemParameters;
        }

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }

        public int getShardingTotalCount() {
            return shardingTotalCount;
        }

        public void setShardingTotalCount(int shardingTotalCount) {
            this.shardingTotalCount = shardingTotalCount;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }
    }

}

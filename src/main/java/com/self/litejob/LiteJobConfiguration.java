package com.self.litejob;

import com.self.litejob.config.JobTypeConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@AllArgsConstructor
@Getter
public final class LiteJobConfiguration implements JobRootConfiguration{

    private JobTypeConfiguration jobTypeConfiguration;

    public String getJobName() {
        return jobTypeConfiguration.getJobCoreConfiguration().getJobName();
    }

    public String getDescription() {
        return jobTypeConfiguration.getJobCoreConfiguration().getDescription();
    }

    public String getJobClass() {
        return jobTypeConfiguration.getJobClass();
    }


    public static Builder build() {
        return new Builder();
    }

    @NoArgsConstructor
    public static class Builder {
        private JobTypeConfiguration jobTypeConfiguration;

        public Builder jobTypeConfiguration(JobTypeConfiguration jobTypeConfiguration) {
            this.jobTypeConfiguration = jobTypeConfiguration;
            return this;
        }

        public final LiteJobConfiguration build() {
            return new LiteJobConfiguration(jobTypeConfiguration);
        }
    }
}

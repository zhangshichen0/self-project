package com.self.litejob.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class JobCoreConfiguration {
    /**
     * 任务的名称
     */
    private String jobName;

    /**
     * 任务执行时间
     */
    private String cron;

    /**
     * 任务描述
     */
    private String description;

    public static Builder newBuilder() {
        return new Builder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder {
        /**
         * 任务的名称
         */
        private String jobName;

        /**
         * 任务执行时间
         */
        private String cron;

        /**
         * 任务描述
         */
        private String description;

        public Builder buildJobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        public Builder buildCron(String cron) {
            this.cron = cron;
            return this;
        }

        public Builder buildDescription(String description) {
            this.description = description;
            return this;
        }

        public JobCoreConfiguration build() {
            return new JobCoreConfiguration(jobName, cron, description);
        }
    }
}

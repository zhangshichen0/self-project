package com.self.litejob.test;

import com.self.litejob.JobScheduler;
import com.self.litejob.LiteJobConfiguration;
import com.self.litejob.config.DataflowJobConfiguration;
import com.self.litejob.config.JobCoreConfiguration;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public class TestJobScheduler {

    public static void main(String[] args) {

        LiteJobConfiguration liteJobConfiguration =
                LiteJobConfiguration.build().jobTypeConfiguration(new DataflowJobConfiguration(
                        JobCoreConfiguration.newBuilder().buildCron("*/10 * * * * ?").buildJobName(MyJob.class.getSimpleName()).build(), MyJob.class.getCanonicalName()))
                        .build();

        JobScheduler jobScheduler = new JobScheduler(liteJobConfiguration);
        jobScheduler.init();

    }

}

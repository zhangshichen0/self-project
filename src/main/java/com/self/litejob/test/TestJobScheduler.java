package com.self.litejob.test;

import com.self.litejob.JobScheduler;
import com.self.litejob.LiteJobConfiguration;
import com.self.litejob.config.DataflowJobConfiguration;
import com.self.litejob.config.JobCoreConfiguration;
import com.self.litejob.reg.zookeeper.ZookeeperConfiguration;
import com.self.litejob.reg.zookeeper.ZookeeperRegisterCenter;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public class TestJobScheduler {

    public static void main(String[] args) {

        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration();
        zkConfig.setServerLists("127.0.0.1:2181");
        zkConfig.setNamespace("lite-job-test");

        ZookeeperRegisterCenter zkRegisterCenter = new ZookeeperRegisterCenter(zkConfig);
        zkRegisterCenter.init();

        LiteJobConfiguration liteJobConfiguration =
                LiteJobConfiguration.build().jobTypeConfiguration(new DataflowJobConfiguration(
                        JobCoreConfiguration.newBuilder().buildCron("*/10 * * * * ?").buildJobName(MyJob.class.getSimpleName()).build(), MyJob.class.getCanonicalName()))
                        .build();

        JobScheduler jobScheduler = new JobScheduler(liteJobConfiguration, zkRegisterCenter);
        jobScheduler.init();
    }

}

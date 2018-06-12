package com.self.litejob.config;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public interface JobTypeConfiguration {

    /**
     * 获得任务类型
     * @return
     */
    JobType getJobType();

    /**
     * 获得核心配置
     * @return
     */
    JobCoreConfiguration getJobCoreConfiguration();

    /**
     * 获得执行任务类
     * @return
     */
    String getJobClass();
}

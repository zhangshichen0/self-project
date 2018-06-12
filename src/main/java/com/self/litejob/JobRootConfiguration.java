package com.self.litejob;

import com.self.litejob.config.JobTypeConfiguration;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public interface JobRootConfiguration {
    /**
     * 获取不同类型配置
     * @return
     */
    JobTypeConfiguration getJobTypeConfiguration();
}

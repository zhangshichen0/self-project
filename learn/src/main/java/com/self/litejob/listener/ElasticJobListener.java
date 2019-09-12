package com.self.litejob.listener;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public interface ElasticJobListener {

    /**
     * 任务执行前操作
     */
    void beforeJobExecutor();

    /**
     * 任务执行后操作
     */
    void afterJobExecutor();
}

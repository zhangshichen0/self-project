package com.self.litejob.executor;

/**
 * 任务门面类
 *
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public interface JobFacade {

    /**
     * 定义任务执行前的操作
     */
    void beforeJobExecutor();

    /**
     * 定义任务执行后的操作
     */
    void afterJobExecutor();
}

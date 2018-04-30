package com.self.threadpool;

import java.util.List;

/**
 * @author shichen
 * @create 2018/3/2
 * @desc
 */
public interface ThreadPool {

    /**
     * 任务执行
     *
     * @param task
     */
    void execute(Runnable task);

    /**
     * 任务执行
     *
     * @param tasks
     */
    void execute(Runnable[] tasks);

    /**
     * 任务执行
     *
     * @param taskList
     */
    void execute(List<Runnable> taskList);

    /**
     * 获取执行任务的个数
     *
     * @return
     */
    int getExecuteTaskNum();

    /**
     * 返回任务队列的长度，即还没有被执行的任务的个数
     *
     * @return
     */
    int getWaitTaskNum();

    /**
     * 返回工作线程数
     *
     * @return
     */
    int getWorkThreadNum();

    /**
     * 销毁线程池
     */
    void destroy();
}

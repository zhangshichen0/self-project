package com.self.threadpool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shichen
 * @create 2018/3/2
 * @desc
 */
public class ThreadPoolManager implements ThreadPool {

    /**
     * 工作线程的数量
     */
    private static int coreThreadNum = 5;

    /**
     * 存放任务队列
     */
    private BlockingQueue<Runnable> taskQueue;

    /**
     * 执行的任务的数量
     */
    private static volatile int executeTaskNum = 0;

    /**
     * 线程数量
     */
    private AtomicInteger threadNum = new AtomicInteger(0);

    /**
     * 工作线程数组
     */
    private Worker[] workers;

    /**
     * 线程池框架实例
     */
    private static ThreadPoolManager threadPoolManager;

    private static Lock lock = new ReentrantLock();


    private ThreadPoolManager() {
        this(5, new LinkedBlockingQueue<>(10));
    }

    private ThreadPoolManager(int coreThreadNum2, BlockingQueue<Runnable> blockingQueue) {
        if (coreThreadNum2 > 0) {
            coreThreadNum = coreThreadNum2;
        }
        this.taskQueue = blockingQueue;
        workers = new Worker[coreThreadNum];

        //初始化线程池
        for (int i = 0; i < coreThreadNum; i++) {
            workers[i] = new Worker();
            workers[i].setName("Thread-worker" + threadNum.incrementAndGet());
            System.out.println("初始化线程数" + (i + 1) + "/" + coreThreadNum + ",当前线程的名字为：" + workers[i].getName());
            workers[i].start();
        }

    }

    /**
     * 获取实例
     *
     * @return
     */
    public static ThreadPoolManager getThreadPoolManager(int coreThreadNum, BlockingQueue<Runnable> blockingQueue) {
        lock.lock();
        if (null == threadPoolManager) {
            threadPoolManager = new ThreadPoolManager(coreThreadNum, blockingQueue);
        }
        lock.unlock();
        return threadPoolManager;
    }

    /**
     * 任务执行
     *
     * @param task
     */
    @Override
    public void execute(Runnable task) {
        if (null == task) {
            throw new NullPointerException("任务为空");
        }
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 任务执行
     *
     * @param tasks
     */
    @Override
    public void execute(Runnable[] tasks) {
        if (null == tasks || tasks.length == 0) {
            throw new NullPointerException("任务为空");
        }
        Arrays.stream(tasks).forEach(task -> {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 任务执行
     *
     * @param taskList
     */
    @Override
    public void execute(List<Runnable> taskList) {
        if (null == taskList || taskList.size() == 0) {
            throw new NullPointerException("任务为空");
        }
        taskList.forEach(task -> {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取执行任务的个数
     *
     * @return
     */
    @Override
    public int getExecuteTaskNum() {
        return executeTaskNum;
    }

    /**
     * 返回任务队列的长度，即还没有被执行的任务的个数
     *
     * @return
     */
    @Override
    public int getWaitTaskNum() {
        return taskQueue.size();
    }

    /**
     * 返回工作线程数
     *
     * @return
     */
    @Override
    public int getWorkThreadNum() {
        return threadNum.get();
    }

    /**
     * 销毁线程池
     */
    @Override
    public void destroy() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < workers.length; i++) {
            workers[i].stopWork();
            workers[i] = null;
        }
    }

    /**
     * 工作线程
     */
    public class Worker extends Thread {

        private boolean isRunning = true;

        @Override
        public void run() {
            Runnable task = null;
            while (isRunning) {

                if (!taskQueue.isEmpty()) {
                    try {
                        task = taskQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (null != task) {
                    task.run();
                }
                executeTaskNum++;
                task = null;
            }
        }

        public void stopWork() {
            this.isRunning = false;
        }
    }
}

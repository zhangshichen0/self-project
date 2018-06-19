package com.self.litejob.registry;

import com.self.litejob.core.JobInstance;
import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import com.self.litejob.scheduler.JobScheduleController;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 作业注册表
 *
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobRegistry {

    /**
     * 当instance实例改变时  其他线程立即感知可见
     */
    private static volatile JobRegistry instance;

    private ConcurrentHashMap<String, JobScheduleController> schedulerControllerMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, JobInstance> jobInstanceMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, CoordinatorRegistryCenter> regCenterMap = new ConcurrentHashMap<>();

    /**
     * 单例模式
     *
     * @return
     */
    public static JobRegistry getInstance() {
        if (instance == null) {
            //类锁
            synchronized (JobRegistry.class) {
                if(null == instance) {
                    instance = new JobRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * 添加作业调度控制器.
     *
     * @param jobName 作业名称
     * @param jobScheduleController 作业调度控制器
     * @param regCenter 注册中心
     */
    public void registerJob(final String jobName, final JobScheduleController jobScheduleController, final CoordinatorRegistryCenter regCenter) {
        schedulerControllerMap.put(jobName, jobScheduleController);
        regCenterMap.put(jobName, regCenter);
        //添加cache的路径
        regCenter.addCacheData("/" + jobName);
    }

    public void addJobInstance(String jobName, JobInstance jobInstance) {
        jobInstanceMap.put(jobName, jobInstance);
    }

    public JobInstance getJobInstance(String jobName) {
        return jobInstanceMap.get(jobName);
    }
}

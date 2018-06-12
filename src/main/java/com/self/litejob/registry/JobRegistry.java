package com.self.litejob.registry;

import com.self.litejob.LiteJobConfiguration;
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

    private ConcurrentHashMap<String, LiteJobConfiguration> configMap = new ConcurrentHashMap();
    private ConcurrentHashMap<String, JobScheduleController> schedulerControllerMap = new ConcurrentHashMap<>();

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


    public void addConfiguration(LiteJobConfiguration liteJobConfiguration) {
        configMap.put(liteJobConfiguration.getJobName(), liteJobConfiguration);
    }

    public LiteJobConfiguration getConfiguration(String jobName) {
        return configMap.get(jobName);
    }

    public void addSchedulerController(String jobName, JobScheduleController jobScheduleController) {
        schedulerControllerMap.put(jobName, jobScheduleController);
    }
}

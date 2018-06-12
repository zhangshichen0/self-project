package com.self.litejob;

import com.self.litejob.executor.JobFacade;
import com.self.litejob.executor.LiteJobFacade;
import com.self.litejob.job.LiteJob;
import com.self.litejob.listener.ElasticJobListener;
import com.self.litejob.registry.JobRegistry;
import com.self.litejob.scheduler.JobScheduleController;
import com.self.litejob.scheduler.JobShutdownHookPlugin;
import com.self.litejob.scheduler.SchedulerFacade;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public final class JobScheduler {
    public static final String ELASTIC_JOB_DATA_MAP_KEY = "job";

    private static final String JOB_FACADE_DATA_MAP_KEY = "jobFacade";

    private final LiteJobConfiguration liteJobConfiguration;
    private final JobFacade jobFacade;
    private final SchedulerFacade schedulerFacade;

    /**
     * 创建任务调度
     * @param liteJobConfiguration
     * @param elasticJobListeners
     */
    public JobScheduler(final LiteJobConfiguration liteJobConfiguration, final ElasticJobListener... elasticJobListeners) {
        JobRegistry.getInstance().addConfiguration(liteJobConfiguration);
        List<ElasticJobListener> elasticJobListenerList = Arrays.asList(elasticJobListeners);
        this.liteJobConfiguration = liteJobConfiguration;
        jobFacade = new LiteJobFacade(elasticJobListenerList);
        schedulerFacade = new SchedulerFacade();
    }

    public void init() {
        JobScheduleController jobScheduleController = new JobScheduleController(createScheduler(), createJobDetail(liteJobConfiguration.getJobClass()), liteJobConfiguration.getJobName());
        JobRegistry.getInstance().addSchedulerController(liteJobConfiguration.getJobName(), jobScheduleController);
        jobScheduleController.scheduleJob(liteJobConfiguration.getJobTypeConfiguration().getJobCoreConfiguration().getCron());
    }


    public Scheduler createScheduler() {
        Scheduler scheduler = null;
        try {
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
            Properties properties = getProperties();
            stdSchedulerFactory.initialize(properties);
            scheduler = stdSchedulerFactory.getScheduler();
            scheduler.getListenerManager().addTriggerListener(schedulerFacade.createJobListener());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }

    public JobDetail createJobDetail(String jobClass) {
        JobDetail jobDetail = JobBuilder.newJob(LiteJob.class).withIdentity(liteJobConfiguration.getJobName()).withDescription(liteJobConfiguration.getDescription()).build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        jobDataMap.put(JOB_FACADE_DATA_MAP_KEY, jobFacade);
        try {
            jobDataMap.put(ELASTIC_JOB_DATA_MAP_KEY, Class.forName(jobClass).newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jobDetail;
    }

    public Properties getProperties() {
        Properties result = new Properties();
        result.put("org.quartz.threadPool.class", org.quartz.simpl.SimpleThreadPool.class.getName());
        result.put("org.quartz.threadPool.threadCount", "1");
        result.put("org.quartz.scheduler.instanceName", liteJobConfiguration.getJobName());
        result.put("org.quartz.jobStore.misfireThreshold", "1");
        result.put("org.quartz.plugin.shutdownhook.class", JobShutdownHookPlugin.class.getName());
        result.put("org.quartz.plugin.shutdownhook.cleanShutdown", Boolean.TRUE.toString());
        return result;
    }
}

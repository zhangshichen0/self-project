package com.self.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 单机版
 *
 * @author shichen
 * @create 2019-04-26
 * @desc
 */
public class QuartzMainTest {

    private static final String JOB_BEAN = "job";

    public static void main(String[] args) {
        //创建jobDetail1
        JobDetail jobDetail1 = getJobDetail(PrintCurrentTimeJob.class);
        //创建Trigger 每日的9点40触发任务
        CronTrigger cronTrigger1 = getCronTrigger("0/10 * * * * ? ", PrintCurrentTimeJob.class.getSimpleName());

        //创建jobDetail2
        JobDetail jobDetail2 = getJobDetail(LongRunTimeJob.class);
        //创建Trigger 每日的9点40触发任务
        CronTrigger cronTrigger2 = getCronTrigger("0/5 * * * * ? ", LongRunTimeJob.class.getSimpleName());


        //创建调度容器
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            //增加监控器
            scheduler.getListenerManager().addTriggerListener(new JobTriggerListener());
            scheduler.getListenerManager().addSchedulerListener(new JobSchedulerListener());
            scheduler.getListenerManager().addJobListener(new JobListener() {
                @Override
                public String getName() {
                    return "aaaaaaa";
                }

                @Override
                public void jobToBeExecuted(JobExecutionContext context) {
                    System.out.println("jobToBeExecuted:" + context.getJobDetail().getKey().getName());
                }

                @Override
                public void jobExecutionVetoed(JobExecutionContext context) {
                    System.out.println("jobExecutionVetoed:" + context.getJobDetail().getKey().getName());
                }

                @Override
                public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
                    System.out.println("jobWasExecuted:" + context.getJobDetail().getKey().getName());
                }
            });

            //增加调度任务
            //scheduler.scheduleJob(jobDetail1, cronTrigger1);
            scheduler.scheduleJob(jobDetail2, cronTrigger2);
            //开始调度
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public static JobDetail getJobDetail(Class<? extends IJob> clazz) {
        //创建jobDetail
        JobKey jobKey = new JobKey(clazz.getSimpleName());
        JobDetail jobDetail = JobBuilder.newJob(JobBean.class).withIdentity(jobKey).build();
        //可以使用此方式设置Job实现类中的参数
        try {
            jobDetail.getJobDataMap().put(JOB_BEAN, clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobDetail;
    }

    public static CronTrigger getCronTrigger(String cron, String jobName) {
        //创建Trigger 每日的9点40触发任务
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        return cronTrigger;
    }

}

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

    public static void main(String[] args) {
        //创建jobDetail1
        JobDetail jobDetail1 = getJobDetail("cronJob1");
        //创建Trigger 每日的9点40触发任务
        CronTrigger cronTrigger1 = getCronTrigger("0/10 * * * * ? ", "cronJob1");

        //创建jobDetail2
        JobDetail jobDetail2 = getJobDetail("cronJob2");
        //创建Trigger 每日的9点40触发任务
        CronTrigger cronTrigger2 = getCronTrigger("0/5 * * * * ? ", "cronJob2");


        //创建调度容器
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            //增加调度任务
            scheduler.scheduleJob(jobDetail1, cronTrigger1);
            scheduler.scheduleJob(jobDetail2, cronTrigger2);
            //开始调度
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public static JobDetail getJobDetail(String jobName) {
        //创建jobDetail
        JobKey jobKey = new JobKey(jobName);
        JobDetail jobDetail = JobBuilder.newJob(JobBean.class).withIdentity(jobKey).build();
        //可以使用此方式设置Job实现类中的参数
        jobDetail.getJobDataMap().put("param1", "1");
        jobDetail.getJobDataMap().put("param2", "2");
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

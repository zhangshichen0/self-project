package com.self.litejob.scheduler;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@RequiredArgsConstructor
public final class JobScheduleController {

    private final Scheduler scheduler;
    private final JobDetail jobDetail;
    private final String triggerIdentity;


    /**
     * 启动调度
     *
     * @param cron
     */
    public void scheduleJob(final String cron) {
        try {
            if(!scheduler.checkExists(jobDetail.getKey())) {
                scheduler.scheduleJob(jobDetail, createTrigger(cron));
            }
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改了调度时间后，重新设置cron，并重启任务
     * @param cron
     */
    public synchronized void rescheduleJob(final String cron) {
        try {
            CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(TriggerKey.triggerKey(triggerIdentity));
            if (null != cronTrigger && cronTrigger.getCronExpression().equals(cron)) {
                scheduler.rescheduleJob(TriggerKey.triggerKey(triggerIdentity), createTrigger(cron));
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private CronTrigger createTrigger(String cron) {
        return TriggerBuilder.newTrigger().withIdentity(triggerIdentity).withSchedule(CronScheduleBuilder.cronSchedule(cron)
                .withMisfireHandlingInstructionDoNothing()).build();
    }
}

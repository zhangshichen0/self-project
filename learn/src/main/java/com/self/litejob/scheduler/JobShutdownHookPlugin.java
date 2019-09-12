package com.self.litejob.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.plugins.management.ShutdownHookPlugin;
import org.quartz.spi.ClassLoadHelper;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@Slf4j
public class JobShutdownHookPlugin extends ShutdownHookPlugin {

    private String jobName;

    @Override
    public void initialize(String name, Scheduler scheduler, ClassLoadHelper classLoadHelper) throws SchedulerException {
        super.initialize(name, scheduler, classLoadHelper);
        this.jobName = scheduler.getSchedulerName();
    }

    @Override
    public void shutdown() {
        super.shutdown();
        log.info("【任务停止】jobName {} stop", jobName);
    }
}

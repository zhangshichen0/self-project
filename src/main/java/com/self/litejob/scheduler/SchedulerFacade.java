package com.self.litejob.scheduler;

import org.quartz.JobListener;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public final class SchedulerFacade {

    public JobTriggerListener createJobListener() {
        return new JobTriggerListener();
    }

}

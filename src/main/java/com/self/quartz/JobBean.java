package com.self.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author shichen
 * @create 2019-04-26
 * @desc
 */
public class JobBean implements Job {

    private IJob job;

    public JobBean() {
    }

    public void setJob(IJob job) {
        this.job = job;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        job.process();
    }
}

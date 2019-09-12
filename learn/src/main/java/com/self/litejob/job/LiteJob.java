package com.self.litejob.job;

import com.self.litejob.executor.JobExecutorFactory;
import com.self.litejob.executor.JobFacade;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 实现了quartz的Job类，执行任务的入口
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public class LiteJob implements Job {

    @Setter
    private com.self.litejob.executor.Job job;

    @Setter
    private JobFacade jobFacade;

    /**
     * 执行任务
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobExecutorFactory.getJobExecutorFactory(job, jobFacade).execute();
    }
}

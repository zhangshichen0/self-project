package com.self.litejob.executor;

import com.self.litejob.job.LiteJob;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobExecutorFactory {

    public static final JobExecutor getJobExecutorFactory(Job job, JobFacade jobFacade) {
        JobExecutor jobExecutor = null;
        if (job instanceof DataflowJob) {
            jobExecutor = new DataflowJobExecutor((DataflowJob) job, jobFacade);
        }
        return jobExecutor;
    }

}

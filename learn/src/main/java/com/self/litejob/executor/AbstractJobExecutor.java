package com.self.litejob.executor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@Slf4j
public abstract class AbstractJobExecutor implements JobExecutor {

    private JobFacade jobFacade;

    public AbstractJobExecutor(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    @Override
    public void execute() {
        jobFacade.beforeJobExecutor();
        this.process();
        jobFacade.afterJobExecutor();
    }

    /**
     * 具体的处理任务逻辑
     */
    protected abstract void process();
}

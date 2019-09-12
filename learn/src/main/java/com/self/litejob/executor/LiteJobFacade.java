package com.self.litejob.executor;

import com.self.litejob.listener.ElasticJobListener;

import java.util.List;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public final class LiteJobFacade implements JobFacade {

    private final List<ElasticJobListener> elasticJobListenerList;

    public LiteJobFacade(List<ElasticJobListener> elasticJobListenerList) {
        this.elasticJobListenerList = elasticJobListenerList;
    }

    @Override
    public void beforeJobExecutor() {
        if (!elasticJobListenerList.isEmpty()) {
            for (ElasticJobListener elasticJobListener : elasticJobListenerList) {
                elasticJobListener.beforeJobExecutor();
            }
        }
    }

    @Override
    public void afterJobExecutor() {
        if (!elasticJobListenerList.isEmpty()) {
            for (ElasticJobListener elasticJobListener : elasticJobListenerList) {
                elasticJobListener.afterJobExecutor();
            }
        }
    }
}

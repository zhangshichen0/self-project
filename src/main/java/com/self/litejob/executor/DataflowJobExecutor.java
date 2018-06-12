package com.self.litejob.executor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@Slf4j
public class DataflowJobExecutor extends AbstractJobExecutor {

    private final DataflowJob<Object> dataflowJob;

    public DataflowJobExecutor(final DataflowJob<Object> dataflowJob, final JobFacade jobFacade) {
        super(jobFacade);
        this.dataflowJob = dataflowJob;
    }

    /**
     * 处理数据
     */
    @Override
    protected void process() {
         this.oneOffExecute();
    }

    private void oneOffExecute() {
        List<Object> dataList = this.fetchData();
        if (CollectionUtils.isNotEmpty(dataList)) {
            processData(dataList);
        }
    }

    /**
     * 获取数据
     *
     * @return
     */
    private List<Object> fetchData() {
        return dataflowJob.fetchData();
    }

    /**
     * 处理数据
     *
     * @param dataList
     */
    private void processData(List<Object> dataList) {
        dataflowJob.processData(dataList);
    }
}

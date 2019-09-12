package com.self.litejob.executor;

import java.util.List;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public interface DataflowJob<T> extends Job {

    /**
     * 查询要执行的数据列表
     * @return
     */
    List<T> fetchData();

    /**
     * 处理数据
     *
     * @param data
     */
    void processData(List<T> data);
}

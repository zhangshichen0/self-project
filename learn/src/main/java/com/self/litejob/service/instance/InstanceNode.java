package com.self.litejob.service.instance;

import com.self.litejob.registry.JobRegistry;
import com.self.litejob.storage.JobNodePath;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
public class InstanceNode {

    private static final String ROOT = "instances";
    private static final String INSTANCES = ROOT + "/%s";

    private final JobNodePath jobNodePath;
    private final String jobName;

    public InstanceNode(JobNodePath jobNodePath, String jobName) {
        this.jobNodePath = jobNodePath;
        this.jobName = jobName;
    }

    /**
     * 获取实例的根路径
     * @return
     */
    public String getInstanceFullPath() {
        return jobNodePath.getFullPath(ROOT);
    }

    /**
     * 获取实例的路径
     *
     * @return
     */
    public String getLocalInstanceNode(){
        return String.format(INSTANCES, JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId());
    }

}

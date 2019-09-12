package com.self.litejob.storage;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
public final class JobNodePath {
    private static final String INSTANCES_NODE = "instances";

    private final String jobName;

    public JobNodePath(String jobName) {
        this.jobName = jobName;
    }

    public String getFullPath(String path) {
        return String.format("/%s/%s", jobName, path);
    }
}

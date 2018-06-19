package com.self.litejob.service.config;

import com.self.litejob.storage.JobNodePath;

/**
 * config节点
 *
 * @author shichen
 * @create 2018/6/19
 * @desc
 */
public final class ConfigNode {
    public final static String ROOT = "config";

    private final JobNodePath jobNodePath;

    public ConfigNode(final String jobName) {
        this.jobNodePath = new JobNodePath(jobName);
    }

    /**
     * 判断路径是否为config路径
     *
     * @param path
     * @return
     */
    public boolean isConfigPath(final String path) {
        return this.getConfigFullPath().equals(path);
    }

    /**
     * 获取config全路径 /${jobName}/config
     * @return
     */
    private final String getConfigFullPath() {
        return jobNodePath.getFullPath(ROOT);
    }
}

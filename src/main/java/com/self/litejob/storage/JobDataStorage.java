package com.self.litejob.storage;

import com.self.litejob.reg.base.CoordinatorRegistryCenter;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
public final class JobDataStorage {

    private final CoordinatorRegistryCenter registryCenter;
    private final JobNodePath jobNodePath;

    public JobDataStorage(CoordinatorRegistryCenter registryCenter, String jobName) {
        this.registryCenter = registryCenter;
        this.jobNodePath = new JobNodePath(jobName);
    }

    /**
     * 持久化临时节点
     *
     * @param node
     */
    public void fillPersistEphemeral(final String node, final Object value) {
        registryCenter.persistEphemeral(jobNodePath.getFullPath(node), value.toString());
    }
}

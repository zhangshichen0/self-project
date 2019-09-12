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
    public void fillPersist(final String node, final Object value) {
        registryCenter.persist(jobNodePath.getFullPath(node), value.toString());
    }

    /**
     * 判断节点是否存在
     * @param node
     * @return
     */
    public boolean isJobNodeExisted(final String node) {
        return registryCenter.isExisted(this.jobNodePath.getFullPath(node));
    }

    /**
     * 直接从zookeeper中获取节点下的value
     * @param node
     * @return
     */
    public String getJobNodeDataDirectly(final String node) {
        return registryCenter.getDirectly(this.jobNodePath.getFullPath(node));
    }

    public String getJobNodeData(final String node) {
        return registryCenter.get(this.jobNodePath.getFullPath(node));
    }

    /**
     *
     * @param node
     */
    public void removeJobNodeIfExisted(final String node) {
        if (this.isJobNodeExisted(node)) {
            registryCenter.remove(this.jobNodePath.getFullPath(node));
        }
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

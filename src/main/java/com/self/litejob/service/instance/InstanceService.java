package com.self.litejob.service.instance;

import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import com.self.litejob.storage.JobDataStorage;
import com.self.litejob.storage.JobNodePath;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
public class InstanceService {

    private final JobNodePath jobNodePath;
    private final InstanceNode instanceNode;
    private final JobDataStorage jobDataStorage;

    public InstanceService(CoordinatorRegistryCenter registryCenter, String jobName) {
        this.jobNodePath = new JobNodePath(jobName);
        this.instanceNode = new InstanceNode(jobNodePath, jobName);
        this.jobDataStorage = new JobDataStorage(registryCenter, jobName);
    }

    /**
     * 持久化节点到zookeeper
     */
    public void persistOnline() {
        jobDataStorage.fillPersistEphemeral(instanceNode.getLocalInstanceNode(), "");
    }

}

package com.self.litejob.zklistener;

import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import com.self.litejob.service.instance.InstanceNodeListenerManager;

/**
 * 所有listenerManager的集合
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
public final class ListenerManager {

    private final InstanceNodeListenerManager instanceNodeListenerManager;

    public ListenerManager(CoordinatorRegistryCenter registryCenter, String jobName) {
        this.instanceNodeListenerManager = new InstanceNodeListenerManager(registryCenter, jobName);
    }

    /**
     * 开始所有的监听
     */
    public void startAllListeners() {
        this.instanceNodeListenerManager.start();
    }

}

package com.self.litejob.service.instance;

import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import com.self.litejob.zklistener.AbstractJobListener;
import com.self.litejob.zklistener.AbstractJobListenerManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
@Slf4j
public final class InstanceNodeListenerManager extends AbstractJobListenerManager {

    public InstanceNodeListenerManager(CoordinatorRegistryCenter registryCenter, String jobName) {
        super(registryCenter, jobName);
    }

    @Override
    public void start() {
        this.addJobListener(new InstanceNodeListener());
    }

    /**
     * instances节点变化监听器
     */
    private final class InstanceNodeListener extends AbstractJobListener {

        @Override
        protected void dataChanged(String path, TreeCacheEvent.Type event, String data) {
            log.info("【监控节点变化】path {}, event {}, data {}", path, event, data);
            System.out.println("【监控节点变化】path {}, event {}, data {}" + path + event + data);
        }
    }
}

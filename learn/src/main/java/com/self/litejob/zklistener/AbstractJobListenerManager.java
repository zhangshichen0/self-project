package com.self.litejob.zklistener;

import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import org.apache.curator.framework.recipes.cache.TreeCache;

import java.util.Objects;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
public abstract class AbstractJobListenerManager {

    private final CoordinatorRegistryCenter registryCenter;
    private final String jobName;

    protected AbstractJobListenerManager(CoordinatorRegistryCenter registryCenter, String jobName) {
        this.registryCenter = registryCenter;
        this.jobName = jobName;
    }

    /**
     * 给缓存的路径增加监控器
     * @param listener
     */
    protected void addJobListener(AbstractJobListener listener) {
        TreeCache treeCache = (TreeCache) registryCenter.getRawCache("/" + jobName);
        if (Objects.nonNull(treeCache)) {
            treeCache.getListenable().addListener(listener);
        }
    }

    /**
     * 开始监听
     */
    protected abstract void start();
}

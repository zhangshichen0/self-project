package com.self.litejob.scheduler;


import com.self.litejob.LiteJobConfiguration;
import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import com.self.litejob.service.config.ConfigService;
import com.self.litejob.service.instance.InstanceService;
import com.self.litejob.zklistener.ListenerManager;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public final class SchedulerFacade {

    private final InstanceService instanceService;
    private final ConfigService configService;
    private final ListenerManager listenerManager;


    public SchedulerFacade(CoordinatorRegistryCenter registryCenter, String jobName) {
        this.instanceService = new InstanceService(registryCenter, jobName);
        this.configService = new ConfigService(registryCenter, jobName);
        this.listenerManager = new ListenerManager(registryCenter, jobName);
    }

    public void registerStartUpInfo() {
        this.listenerManager.startAllListeners();
        this.instanceService.persistOnline();
    }

    /**
     * 更新信息
     * @param liteJobConfiguration
     * @return
     */
    public LiteJobConfiguration updateLiteJobConfig(final LiteJobConfiguration liteJobConfiguration) {
        this.configService.persist(liteJobConfiguration);
        return configService.load(false);
    }

    public JobTriggerListener createJobListener() {
        return new JobTriggerListener();
    }

}

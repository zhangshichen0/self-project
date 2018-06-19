package com.self.litejob.service.config;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.self.litejob.LiteJobConfiguration;
import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import com.self.litejob.storage.JobDataStorage;
import joptsimple.internal.Strings;

/**
 * @author shichen
 * @create 2018/6/19
 * @desc
 */
public final class ConfigService {

    private final JobDataStorage jobDataStorage;

    /**
     *
     * @param registryCenter
     * @param jobName
     */
    public ConfigService(CoordinatorRegistryCenter registryCenter, String jobName) {
        this.jobDataStorage = new JobDataStorage(registryCenter, jobName);
    }

    /**
     * 获取配置信息
     *
     * @param fromCache 是否从缓存中获取
     * @return
     */
    public LiteJobConfiguration load(boolean fromCache) {
        String result;
        if (fromCache) {
            result = jobDataStorage.getJobNodeData(ConfigNode.ROOT);
            if (Strings.isNullOrEmpty(result)) {
                result = jobDataStorage.getJobNodeDataDirectly(ConfigNode.ROOT);
            }
        } else {
            result = jobDataStorage.getJobNodeDataDirectly(ConfigNode.ROOT);
        }
        return JSONObject.parseObject(result, LiteJobConfiguration.class);
    }

    /**
     * 持久化config信息到zookeeper
     *
     * @param liteJobConfiguration
     */
    public void persist(final LiteJobConfiguration liteJobConfiguration) {
        //checkConflictJob(liteJobConfiguration);
        if (!jobDataStorage.isJobNodeExisted(ConfigNode.ROOT) || liteJobConfiguration.isOverwrite()) {
            //jobDataStorage.fillPersist(ConfigNode.ROOT, JSONObject.toJSONString(liteJobConfiguration));
        } else {
            jobDataStorage.removeJobNodeIfExisted(ConfigNode.ROOT);
        }
        jobDataStorage.fillPersist(ConfigNode.ROOT, JSONObject.toJSONString(liteJobConfiguration));

    }

    /**
     * 检查配置是否冲突
     *
     * @param liteJobConfiguration
     */
    private void checkConflictJob(LiteJobConfiguration liteJobConfiguration) {
        Optional<LiteJobConfiguration> liteJobConfigFromZk = find();
        //查到不为空
        if (liteJobConfigFromZk.isPresent() && !liteJobConfigFromZk.get().getJobTypeConfiguration().getJobClass().equals(liteJobConfiguration.getJobTypeConfiguration().getJobClass())) {
            throw new RuntimeException("初始化任务信息失败");
        }
    }

    /**
     * 从zk中获取配置
     *
     * @return
     */
    private Optional<LiteJobConfiguration> find() {
        if (!jobDataStorage.isJobNodeExisted(ConfigNode.ROOT)) {
            return Optional.absent();
        }

        //TODO 此处可能会有问题
        LiteJobConfiguration liteJobConfiguration = JSONObject.parseObject(jobDataStorage.getJobNodeDataDirectly(ConfigNode.ROOT), LiteJobConfiguration.class);
        if (null == liteJobConfiguration) {
            jobDataStorage.removeJobNodeIfExisted(ConfigNode.ROOT);
        }
        return Optional.fromNullable(liteJobConfiguration);
    }
}

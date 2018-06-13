package com.self.litejob.reg.base;

import java.util.List;

/**
 * 定义分布式协调注册中心操作
 * @author shichen
 * @create 2018/6/13
 * @desc
 */
public interface CoordinatorRegistryCenter extends RegisterCenter {

    /**
     * 直接从注册中心中拿数据，而不是缓存
     * @param key
     * @return
     */
    String getDirectly(String key);

    /**
     * 获取key下的子节点列表
     *
     * @param key
     * @return
     */
    List<String> getChildrenKeys(String key);

    /**
     * 获取key下子节点的数量
     *
     * @param key
     * @return
     */
    int getNumChildren(String key);

    /**
     * 持久化临时节点数据
     *
     * @param key
     * @param value
     * @return
     */
    boolean persistEphemeral(String key, String value);

    /**
     * 持久化顺序节点数据
     *
     * @param key
     * @param value
     * @return
     */
    boolean persistSequential(String key, String value);

    /**
     * 持久化临时顺序注册数据.
     *
     * @param key 键
     */
    void persistEphemeralSequential(String key);

    /**
     * 添加需要缓存的路径
     *
     * @param cachePath 需要缓存的路径
     */
    void addCacheData(String cachePath);

    /**
     * 释放本地缓存路径
     * @param cachePath 缓存路径
     */
    void evictCacheData(String cachePath);

    /**
     * 获取路径的缓存对象
     *
     * @param cachePath
     * @return
     */
    Object getRawCache(String cachePath);
}

package com.self.litejob.reg;

/**
 * 定义注册中心基础操作接口
 *
 * @author shichen
 * @create 2018/6/13
 * @desc
 */
public interface RegisterCenter {

    /**
     * 初始化注册中心
     */
    void init();

    /**
     * 关闭注册中心
     */
    void close();

    /**
     * 获取注册数据
     * @param key
     */
    String get(String key);

    /**
     * 判断数据是否存在
     *
     * @param key
     */
    boolean isExisted(String key);

    /**
     * 持久化数据
     *
     * @param key
     * @param value
     * @return
     */
    boolean persist(String key, String value);

    /**
     * 更新数据
     * @param key
     * @param value
     * @return
     */
    boolean update(String key, String value);

    /**
     * 移除注册数据
     *
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     * 获取注册中心当前时间
     * @return
     */
    long getRegistryCenterTime();

    /**
     * 获取注册中心原生客户端
     * @return
     */
    Object getRawClient();
}

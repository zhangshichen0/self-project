package com.self.litejob.reg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 定义zookeeper初始化的需要参数
 * @author shichen
 * @create 2018/6/13
 * @desc
 */
@Setter
@Getter
@RequiredArgsConstructor
public final class ZookeeperConfiguration {

    /**
     * zookeeper服务器集群列表
     */
    private String serverLists;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 等待重试的间隔时间的初始值.
     * 单位毫秒.
     */
    private int baseSleepTimeMilliseconds = 1000;

    /**
     * 等待重试的间隔时间的最大值.
     * 单位毫秒.
     */
    private int maxSleepTimeMilliseconds = 3000;

    /**
     * 最大重试次数.
     */
    private int maxRetries = 3;

    /**
     * 会话超时时间.
     * 单位毫秒.
     */
    private int sessionTimeoutMilliseconds;

    /**
     * 连接超时时间.
     * 单位毫秒.
     */
    private int connectionTimeoutMilliseconds;

    /**
     * 连接Zookeeper的权限令牌.
     * 缺省为不需要权限验证.
     */
    private String digest;
}

package com.self.litejob.reg;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * zookeeper初始化及操作类
 *
 * @author shichen
 * @create 2018/6/13
 * @desc
 */
@Slf4j
public final class ZookeeperRegisterCenter implements CoordinatorRegistryCenter {

    @Getter(value = AccessLevel.PROTECTED)
    private ZookeeperConfiguration zkConfig;

    @Getter
    private CuratorFramework client;

    public ZookeeperRegisterCenter(ZookeeperConfiguration zkConfig) {
        this.zkConfig = zkConfig;
    }

    @Override
    public void init() {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getServerLists())
                .retryPolicy(new ExponentialBackoffRetry(zkConfig.getBaseSleepTimeMilliseconds(), zkConfig.getMaxRetries(), zkConfig.getMaxSleepTimeMilliseconds()))
                .namespace(zkConfig.getNamespace());

        if (zkConfig.getConnectionTimeoutMilliseconds() > 0) {
            builder.connectionTimeoutMs(zkConfig.getConnectionTimeoutMilliseconds());
        }

        if (zkConfig.getSessionTimeoutMilliseconds() > 0) {
            builder.sessionTimeoutMs(zkConfig.getSessionTimeoutMilliseconds());
        }

        //令牌不为空
        if (!Strings.isNullOrEmpty(zkConfig.getDigest())) {
            builder.authorization("digest", zkConfig.getDigest().getBytes(Charsets.UTF_8))
                    .aclProvider(new ACLProvider() {
                        @Override
                        public List<ACL> getDefaultAcl() {
                            //给创建者所有的权限，增删改查
                            return ZooDefs.Ids.CREATOR_ALL_ACL;
                        }

                        @Override
                        public List<ACL> getAclForPath(String path) {
                            return ZooDefs.Ids.CREATOR_ALL_ACL;
                        }
                    });
        }

        //创建客户端
        client = builder.build();
        //开始建立连接
        client.start();
        try {
            //阻塞，直到超时
            boolean isConnected = client.blockUntilConnected(zkConfig.getBaseSleepTimeMilliseconds() * zkConfig.getMaxRetries(), TimeUnit.MILLISECONDS);
            if (!isConnected) {
                //关闭
                client.close();
                log.error("【初始化zookeeper连接】连接zookeeper失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public String getDirectly(String key) {
        return null;
    }

    @Override
    public List<String> getChildrenKeys(String key) {
        return null;
    }

    @Override
    public int getNumChildren(String key) {
        return 0;
    }

    @Override
    public boolean persistEphemeral(String key, String value) {
        return false;
    }

    @Override
    public boolean persistSequential(String key, String value) {
        return false;
    }

    @Override
    public void persistEphemeralSequential(String key) {

    }

    @Override
    public void addCacheData(String cachePath) {

    }

    @Override
    public void evictCacheData(String cachePath) {

    }

    @Override
    public Object getRawCache(String cachePath) {
        return null;
    }



    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean isExisted(String key) {
        return false;
    }

    @Override
    public boolean persist(String key, String value) {
        return false;
    }

    @Override
    public boolean update(String key, String value) {
        return false;
    }

    @Override
    public boolean remove(String key) {
        return false;
    }

    @Override
    public long getRegistryCenterTime() {
        return 0;
    }

    @Override
    public Object getRawClient() {
        return null;
    }
}

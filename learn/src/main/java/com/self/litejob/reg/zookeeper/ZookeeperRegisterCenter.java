package com.self.litejob.reg.zookeeper;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.self.litejob.reg.base.CoordinatorRegistryCenter;
import com.self.litejob.reg.exception.RegExceptionHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    private Map<String, TreeCache> caches = new ConcurrentHashMap<>();

    public ZookeeperRegisterCenter(ZookeeperConfiguration zkConfig) {
        this.zkConfig = zkConfig;
    }

    @Override
    public void init() {
        long startTime = System.currentTimeMillis();
        log.info("【初始化zookeeper连接】开始。。。。");
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
                throw new KeeperException.OperationTimeoutException();
            }
            log.info("【初始化zookeeper连接】结束。。。。,耗时{}ms", System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
    }

    @Override
    public void close() {
        for (Map.Entry<String, TreeCache> cache : caches.entrySet()) {
            cache.getValue().close();
        }

        waitCacheClose();
        client.close();
    }

    private void waitCacheClose() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 直接从zookeeper中获取数据
     *
     * @param key
     * @return
     */
    @Override
    public String getDirectly(final String key) {
        try {
            return new String(client.getData().forPath(key), Charsets.UTF_8);
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return null;
    }

    /**
     * 获取给定key下的子节点列表
     *
     * @param key
     * @return
     */
    @Override
    public List<String> getChildrenKeys(final String key) {
        try {
            List<String> result = client.getChildren().forPath(key);
            result.sort(Comparator.reverseOrder());
            return result;
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return null;
    }

    /**
     * 获取给定key下的子节点数量
     *
     * @param key
     * @return
     */
    @Override
    public int getNumChildren(final String key) {
        try {
            Stat stat = client.checkExists().forPath(key);
            if (null != stat) {
                return stat.getNumChildren();
            }
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return 0;
    }

    /**
     * 节点是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean isExisted(final String key) {
        try {
            Stat stat = client.checkExists().forPath(key);
            if (null != stat) {
                return true;
            }
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return false;
    }

    /**
     * 持久化临时节点
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean persistEphemeral(final String key, String value) {
        try {
            if (isExisted(key)) {
                //删除
                client.delete().deletingChildrenIfNeeded().forPath(key);
            }
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(key, value.getBytes(Charsets.UTF_8));
            return true;
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return false;
    }

    @Override
    public boolean persistSequential(final String key, String value) {

        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(key, value.getBytes(Charsets.UTF_8));
            return true;
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return false;
    }

    @Override
    public void persistEphemeralSequential(final String key) {
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(key);
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
    }

    @Override
    public void addCacheData(String cachePath) {
        TreeCache cache = new TreeCache(client, cachePath);
        try {
            cache.start();
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        caches.put(cachePath + "/", cache);
    }

    @Override
    public void evictCacheData(String cachePath) {
        TreeCache cache = caches.remove(cachePath + "/");
        if (null != cache) {
            cache.close();
        }
    }

    @Override
    public Object getRawCache(String cachePath) {
        return caches.get(cachePath + "/");
    }

    @Override
    public String get(final String key) {
        TreeCache cache = findFromCache(key);
        if (null == cache) {
            return this.getDirectly(key);
        }
        ChildData childData = cache.getCurrentData(key);
        if (null != childData) {
            return childData.getData() == null ? null : new String(childData.getData(), Charsets.UTF_8);
        }
        return getDirectly(key);
    }

    /**
     * 获取TreeCache对象
     *
     * @param key
     * @return
     */
    private TreeCache findFromCache(final String key) {
        for(Map.Entry<String, TreeCache> entry : caches.entrySet()) {
            if(key.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean persist(final String key, String value) {
        try {
            if (!isExisted(key)) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(key, value.getBytes(Charsets.UTF_8));
            } else {
                update(key, value);
            }
            return true;
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return false;
    }

    @Override
    public boolean update(final String key, String value) {
        try {
            client.inTransaction().check().forPath(key).and().setData().forPath(key, value.getBytes(Charsets.UTF_8)).and().commit();
            return true;
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return false;
    }

    @Override
    public boolean remove(final String key) {
        try {
            client.delete().deletingChildrenIfNeeded().forPath(key);
            return true;
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        return false;
    }

    @Override
    public long getRegistryCenterTime(final String key) {
        long result = 0L;
        persist(key, "");
        try {
            Stat stat = client.checkExists().forPath(key);
            result = stat.getMtime();
        } catch (Exception e) {
            RegExceptionHandler.handleException(e);
        }
        //验证是否获得到时间
        Preconditions.checkArgument(result != 0L, "Cannot get registry center time.");
        return result;
    }

    @Override
    public Object getRawClient() {
        return client;
    }
}

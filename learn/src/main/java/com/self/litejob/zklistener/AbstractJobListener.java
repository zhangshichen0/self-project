package com.self.litejob.zklistener;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

import java.util.Objects;

/**
 * 监听器 用于监听节点变化
 *
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
public abstract class AbstractJobListener implements TreeCacheListener {

    @Override
    public void childEvent(final CuratorFramework client, final TreeCacheEvent event) throws Exception {
        ChildData data = event.getData();
        if (Objects.isNull(data)) {
            return;
        }

        String path = data.getPath();
        if (Strings.isNullOrEmpty(path)) {
            return;
        }
        dataChanged(path, event.getType(), null == data.getData() ? "" : new String(data.getData(), Charsets.UTF_8));
    }

    /**
     * 监听的子节点信息变化，调用
     *
     * @param path
     * @param event
     * @param data
     */
    protected abstract void dataChanged(final String path, final TreeCacheEvent.Type event, final String data);
}

package com.self.mybatis;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shichen
 * @create 2018/9/13
 * @desc
 */
public class MapperRegistry {

    /**
     * 存放每个接口对应的生成代理类工厂对象
     */
    private Map<Class<?>, MapperProxyFactory<?>> mapperProxyFactoryMap = new ConcurrentHashMap<>();

    /**
     * 获取代理对象
     *
     * @param mapperInterface
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> mapperInterface) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) mapperProxyFactoryMap.get(mapperInterface);
        if (Objects.isNull(mapperProxyFactory)) {
            throw new RuntimeException("error");
        }
        return mapperProxyFactory.newInstance();
    }

    /**
     * 添加代理对象
     * @param mapperInterface
     * @param <T>
     */
    public <T> void addMapper(Class<T> mapperInterface) {
        if (mapperInterface.isInterface()) {
            if (!hasMapper(mapperInterface)) {
                mapperProxyFactoryMap.put(mapperInterface, new MapperProxyFactory<>(mapperInterface));
            }
        }
    }

    /**
     * 缓存下是否已经生成接口的代理工厂生成类
     *
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> boolean hasMapper(Class<T> tClass) {
        return mapperProxyFactoryMap.containsKey(tClass);
    }
}

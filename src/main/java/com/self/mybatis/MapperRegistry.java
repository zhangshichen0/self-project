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


    private <T> boolean hasMapper(Class<T> tClass) {
        return mapperProxyFactoryMap.containsKey(tClass);
    }
}

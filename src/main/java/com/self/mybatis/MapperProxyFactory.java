package com.self.mybatis;

/**
 * @author shichen
 * @create 2018/9/13
 * @desc
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;


    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(MapperProxy<T> mapperProxy) {
        return mapperProxy.newInstance();
    }

    public T newInstance() {
        MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface);
        return newInstance(mapperProxy);
    }

}

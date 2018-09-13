package com.self.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shichen
 * @create 2018/9/13
 * @desc
 */
public class MapperProxy<T> implements InvocationHandler {

    private Class<T> mapperClass;
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(Class<T> mapperClass) {
        this.mapperClass = mapperClass;
        this.methodCache = new ConcurrentHashMap<>();
    }

    public T newInstance() {
        return (T)Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method class:" + method.getDeclaringClass().getSimpleName());
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(method, args);
        }

        MapperMethod mapperMethod = cachedMapperMethod(method);
        if (Objects.isNull(mapperMethod)) {
            throw new RuntimeException("not exist for this method: " + method.getName());
        }

        return mapperMethod.execute();
    }

    /**
     * 缓存method
     * @param method
     * @return
     */
    private MapperMethod cachedMapperMethod(Method method) {
        //先从cache中先获取method
        MapperMethod mapperMethod = methodCache.get(method);
        if (Objects.isNull(mapperMethod)) {
            mapperMethod = new MapperMethod(method, mapperClass);
        }
        return mapperMethod;
    }
}

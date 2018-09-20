package com.self.spring.aop.simpleAop.container;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shichen
 * @create 2018/9/19
 * @desc
 */
public class ProxyFactory {
    
    private Map<String, Object> proxyBeans = new ConcurrentHashMap<>();

    /**
     * 返回bean
     *
     * @param classPath
     * @return
     */
    public Object getBean(String classPath) {
        return proxyBeans.get(classPath);
    }

    /**
     * 创建代理对象
     * @param scanPackage
     * @return
     */
    public boolean createProxyBeans(String scanPackage) {
        Package pak = Package.getPackage(scanPackage);
        List<Class<?>> classes = ScannerUtil.getAllClassByPackageName(pak);
        classes.stream().forEach(clazzP -> {
            try {
                newInstance(clazzP);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    /**
     * 为类生成实例并生成代理对象
     *
     * @param clazzP
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public Object newInstance(Class<?> clazzP) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String clazzPath = clazzP.getName();
        Object proxyInstance = proxyBeans.get(clazzPath);
        if (Objects.isNull(proxyInstance)) {
            Class clazz = Class.forName(clazzPath);
            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces.length == 0) {
                throw new IllegalArgumentException(clazz.getName() + " is not implements an interface");
            }

            if (Objects.nonNull(clazz.getAnnotation(Instance.class))) {
                Object target = clazz.getConstructor().newInstance();
                JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(target);
                proxyInstance = jdkDynamicAopProxy.newInstance(interfaces);
                proxyBeans.put(clazz.getInterfaces()[0].getName(), proxyInstance);
            } else {
                throw new IllegalArgumentException(clazz.getName() + " not have @Instance annotation");
            }
        } 
        
        return proxyInstance;
    }

}

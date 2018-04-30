package com.self.proxy.dynamicproxy.selfimpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(String path, ClassLoader classLoader) throws MalformedURLException {
        super(new URL[]{new URL(path)}, classLoader);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}

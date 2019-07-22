package com.self.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author shichen
 * @create 2018-12-14
 * @desc
 */
public class ServiceBootstrap {

    public static <S> S loadFirst(Class<S> clazz) {
        Iterator<S> iterator = loadAll(clazz);
        if (!iterator.hasNext()) {
            throw new IllegalStateException(String.format(
                    "No implementation defined in /META-INF/services/%s, please check whether the file exists and has the right implementation class!",
                    clazz.getName()));
        }
        return iterator.next();
    }

    public static <S> Iterator<S> loadAll(Class<S> clazz) {
        ServiceLoader<S> loader = ServiceLoader.load(clazz);

        //serviceLoader是启动类加载器加载
        System.out.println(loader.getClass().getClassLoader());

        return loader.iterator();
    }

}

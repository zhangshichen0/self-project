package com.self.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 测试不同类加载器加载的class对象是否相等，用于证明class对象相等是由类加载器加类来唯一确定的
 *
 * @author shichen
 * @create 2019-07-18
 * @desc
 */
public class ClassloaderMainTest {

    public static void main(String[] args) {
        ClassLoader classLoader = new ClassLoader(null) {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1).concat(".class");
                InputStream is = getClass().getResourceAsStream(fileName);
                if (Objects.isNull(is)) {
                    return super.loadClass(name);
                }
                try {
                    byte [] bytes = new byte [is.available()];
                    is.read(bytes);
                    return this.defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };

        try {
            Object obj = classLoader.loadClass("com.self.classloader.ClassloaderMainTest").newInstance();
            System.out.println(obj.getClass());

            //这两个对象是被同一个类加载器加载
            ClassloaderMainTest classloaderMainTest = new ClassloaderMainTest();
            ClassloaderMainTest classloaderMainTest1 = new ClassloaderMainTest();

            System.out.println(classloaderMainTest.getClass() == classloaderMainTest1.getClass());

            //两个对象指向的class是不相等的
            System.out.println(obj instanceof com.self.classloader.ClassloaderMainTest);


            //打印出的类加载器不同
            System.out.println(classloaderMainTest.getClass().getClassLoader());
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

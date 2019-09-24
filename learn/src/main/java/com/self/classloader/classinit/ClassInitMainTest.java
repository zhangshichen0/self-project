package com.self.classloader.classinit;

/**
 * @author shichen
 * @create 2019-09-24
 * @desc
 */
public class ClassInitMainTest {

    public static void main(String[] args) {

        //以下三种方式 不会触发类的初始化
        Class clazz = ClassInitChild.class;

        try {
            Class clazz1 = Class.forName("com.self.classloader.classinit.ClassInitChild", false, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class clazz2 = Thread.currentThread().getContextClassLoader().loadClass("com.self.classloader.classinit.ClassInitChild");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

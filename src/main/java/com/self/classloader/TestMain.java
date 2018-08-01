package com.self.classloader;

/**
 * @author shichen
 * @create 2018/8/1
 * @desc
 */
public class TestMain {

    public static void main(String[] args) {
        ClassLoader classLoader = TestMain.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }

}

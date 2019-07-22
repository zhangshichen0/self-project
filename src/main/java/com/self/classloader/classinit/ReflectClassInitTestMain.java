package com.self.classloader.classinit;

/**
 * 反射触发类的初始化
 *
 * @author shichen
 * @create 2019-07-22
 * @desc
 */
public class ReflectClassInitTestMain {

    static {
        System.out.println("ReflectClassInitTestMain init");
    }

    public static void main(String[] args) {
        try {
            //引起类的初始化
            Class.forName("com.self.classloader.classinit.ReflectClass");

            //使用loadclass并不会引起类的初始化，只是加载到内存，不会执行类构造器
            ReflectClassInitTestMain.class.getClassLoader().loadClass("com.self.classloader.classinit.ReflectClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class ReflectClass {

    static {
        System.out.println("ReflectClass init");
    }

    public int add(int a) {
        return a + 1;
    }
}

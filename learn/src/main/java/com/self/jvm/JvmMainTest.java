package com.self.jvm;

/**
 * @author shichen
 * @create 2018/9/20
 * @desc
 */
public class JvmMainTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("com.self.jvm.Hello");

        Class clazz1 = Class.forName("com.self.jvm.Hello");

        //返回true，说明类只会被加载一次，并且在方法区中类元数据表中会记录此类对应Class对象的引用
        System.out.println(clazz == clazz1);
    }

}

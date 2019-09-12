package com.self.classloader;

/**
 * @author shichen
 * @create 2019-07-17
 * @desc
 */
public class ConstantTestMain {

    static class Constant {
        static {
            System.out.println("Constant init");
        }
        public static final String a = "ssssss";
    }

    public static void main(String[] args) {

        //当a为String或者是基本类型常量时，不会引起Constant的加载
        System.out.println(Constant.a);
    }

}

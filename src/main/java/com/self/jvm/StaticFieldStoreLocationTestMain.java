package com.self.jvm;

/**
 * 静态变量分配在堆中，并且被移到了Class对象中
 * https://blog.csdn.net/xu_jl1997/article/details/89433916
 *
 *
 * @author shichen
 * @create 2019-08-02
 * @desc
 */
public class StaticFieldStoreLocationTestMain {

    private static String a = "a";

    static {
        for (int i = 0; i < 100000; i ++) {
            a = a + i;
        }
    }

    public static void main(String[] args) {
        StaticFieldStoreLocationTestMain staticFieldStoreLocationTestMain = new StaticFieldStoreLocationTestMain();
    }

}

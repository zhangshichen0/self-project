package com.self.classloader;

/**
 * @author shichen
 * @create 2019-07-17
 * @desc
 */
public class Testxx {

    public static void main(String[] args) {
        //子类引用父类静态字段时，子类不会被加载
        System.out.println(MyTest.b);
    }

}

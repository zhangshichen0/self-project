package com.self.classloader.classinit;

/**
 * 如果常量静态字段为实例常量，则会加载Constant类
 * 若字段为基本类型或者String，则不会加载Constant类
 *
 * 通过观察字节码也可以确定类是否被初始化
 *
 * @author shichen
 * @create 2019-07-22
 * @desc
 */
public class StaticFinalObjectClassInitTest {

    public static void main(String[] args) {
        //不会引起类Constant初始化
        System.out.println(Constant.A);

        //会引起类Constant初始化
        //System.out.println(Constant.USER);
    }

}

class Constant {

    static {
        System.out.println("constant init");
    }

    public static final User USER = new User();

    public static final String A = "1";

}

class User {

    static {
        System.out.println("user init");
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

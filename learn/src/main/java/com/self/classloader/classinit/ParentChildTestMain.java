package com.self.classloader.classinit;

/**
 * 子类初始化，需要先触发父类的初始化
 *
 * @author shichen
 * @create 2019-07-22
 * @desc
 */
public class ParentChildTestMain {

    public static void main(String[] args) {
        //Child child = new Child();

        //此种情况下只会加载父类
        System.out.println(Child.m);
    }

}

class Parent {
    protected static int m = 1;

    static {
        System.out.println("parent init");
    }
}

class Child extends Parent {
    static {
        System.out.println("child init");
    }
}

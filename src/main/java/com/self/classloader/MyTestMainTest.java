package com.self.classloader;

/**
 *
 * 子类调用或者设置父类的静态字段或者调用父类的静态方法时仅仅初始化父类，而不初始化子类。
 * 同样读取final修饰的常量不会进行类的初始化。
 *
 * @author shichen
 * @create 2019-04-23
 * @desc
 */
public class MyTestMainTest {

    public static void main(String[] args) {

        //引用父类静态常量，并不会引起父类初始化，因为静态常量在编译时已经被加入到引用类class常量池中
        System.out.println(MyTest.c);

        //引用父类静态变量或者方法，只会触发父类初始化，子类不会初始化
        System.out.println(MyTest.b);
    }

}

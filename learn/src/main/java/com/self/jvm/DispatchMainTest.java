package com.self.jvm;

/**
 * 测试jvm动态分派 静态分派
 *
 * 动态分派的代表是重写，即继承
 * 静态分派的代表是重载
 *
 * 进一步划分为动态单分派 静态多分派
 *
 * 单多分派的区分是宗量，宗量主要包括方法描述，和接收者，即具体调用方法的对象
 *
 * @author shichen
 * @create 2019-09-20
 * @desc
 */
public class DispatchMainTest {

    public DispatchMainTest(int a) {
        System.out.println(a);
    }

    public static void main(String[] args) {

        Human man = new Man();
        Human woman = new Woman();

        man.sayHello();
        woman.sayHello();

        //静态分派即使用重载--在编译器即可确定调用的方法
        //注释掉方法sayHi(char)后，编译后字节码文件直接调用的sayHi(int)方法
        DispatchMainTest.sayHi('a');
        DispatchMainTest.sayHi(20);
        DispatchMainTest.sayHi(1L);
    }

    interface Human {
        void sayHello();
    }

    static class Man implements Human {

        @Override
        public void sayHello() {
            System.out.println("man");
        }
    }

    static class Woman implements Human {

        @Override
        public void sayHello() {
            System.out.println("woman");
        }
    }

    //静态分派即使用重载--在编译器即可确定调用的方法

    /**
     * 传char
     */
    public static void sayHi(char a) {
        System.out.println("char " + a);
    }

    public static void sayHi(int a) {
        System.out.println("int " + a);
    }

    public static void sayHi(long a) {
        System.out.println("long " + a);
    }

}

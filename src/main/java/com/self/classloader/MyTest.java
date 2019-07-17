package com.self.classloader;

/**
 * 在运行一个Java程序时，分为类加载过程和实例化过程，即static修饰的代码块、属性、声明为类加载，类的普通属性、代码块、
 * 构造器为实例化过程。其中类加载的代码属于整个类，只执行一次；而实例化过程中的代码属于实例化对象的，每次实例化过程都执行。
 * 实例代码块和实例变量执行过程是按照出现顺序顺序执行
 *
 * 执行顺序类加载先于实例化执行，执行过程自上而下按顺序执行。如果父类有类加载代码，先按前面的规则执行父类的类加载代码，
 * 执行完后再执行自己的类加载代码。如果类加载过程中实例化了对象，则暂停类加载过程，执行实例化代码，实例化完成后继续执
 * 行未执行完的类加载代码。
 *
 * 当类加载代码执行完后，执行实例化代码，实例化过程中不能执行类加载过程。实例化变量先自上而下按顺序执行普通代码块、
 * 属性，最后执行构造器，如果有父类就先按前面的规则执行父类的。
 *
 * 当执行完类加载和实例化代码后，再去执行main方法里的内容。
 * @author shichen
 * @create 2019-04-22
 * @desc
 */
public class MyTest extends Test {

    public static int x = count("静态");

    static {
        System.out.println("静态代码块1");
    }

    public static Testa testa = new Testa();

    public static MyTest test1 = new MyTest("test1");
    public static MyTest test2 = new MyTest("test2");
    public static int n = count("非静态");
    public int a = count("");

    {
        System.out.println("普通代码块1");
    }

    static {
        System.out.println("静态代码块2");
    }

    public static int count(String string) {
        System.out.println("赋值" + string + "变量");
        return 0;
    }

    public MyTest(String string) {
        System.out.println("构造器实例化" + string);
    }

    {
        System.out.println("普通代码块2");
    }

    public static void main(String[] args) {
        System.out.println(Test.b);
        MyTest test3 = new MyTest("Mian");
    }
}

class Test {

    public static int b = 1;

    public static final int c = 2;

    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类普通代码块");
    }

}

class Testa {
    static {
        System.out.println("Testa被加载");
    }
}

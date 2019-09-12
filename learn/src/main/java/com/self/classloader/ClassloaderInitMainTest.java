package com.self.classloader;

/**
 * 测试类加载过程中，是否会加载实例字段
 *
 * @author shichen
 * @create 2019-07-22
 * @desc
 */
public class ClassloaderInitMainTest {

    static {
        System.out.println("ClassloaderInitMainTest class init");
    }


    private TextField field = new TextField();


    {
        System.out.println("ClassloaderInitMainTest object init");
    }

    public static void main(String[] args) {
        //下面创建对象实例增加，则会打印出text field class init + ClassloaderInitMainTest object init 否则不会加载TextField类
        //ClassloaderInitMainTest classloaderInitMainTest = new ClassloaderInitMainTest();
        System.out.println("xxxxx");
    }

}

class TextField {

    static {
        System.out.println("text field class init");
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

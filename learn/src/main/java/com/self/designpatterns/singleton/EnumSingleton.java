package com.self.designpatterns.singleton;

/**
 * 使用枚举实现单例，最安全的方式，能够防止通过序列化和反射，创建多个实例
 *
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public enum EnumSingleton {
    /**
     * 实例
     */
    instance;

    /**
     * 定会类中的操作
     */
    public void otherMethod() {
        System.out.println("2111121");
    }

    public static void main(String[] args) {
        EnumSingleton.instance.otherMethod();
    }
}

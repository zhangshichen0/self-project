package com.self.designpatterns.factory.factorymethod;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public abstract class AbstractFactory {

    /**
     * 工厂方法，用于创建对象
     *
     * @return
     */
    protected abstract Animal factoryMethod();

    /**
     * 可以做一些事情
     */
    public void doSomeThing() {
        Animal animal = factoryMethod();
        //do some thing
        animal.voice();
    }
}

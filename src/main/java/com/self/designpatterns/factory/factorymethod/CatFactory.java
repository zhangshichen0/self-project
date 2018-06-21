package com.self.designpatterns.factory.factorymethod;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class CatFactory extends AbstractFactory {
    /**
     * 工厂方法，用于创建对象
     *
     * @return
     */
    @Override
    protected Animal factoryMethod() {
        return new Cat();
    }
}

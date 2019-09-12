package com.self.designpatterns.factory.factorymethod;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class MainTest {

    public static void main(String[] args) {
        AbstractFactory factory = new DogFactory();
        factory.doSomeThing();

        AbstractFactory factory1 = new CatFactory();
        factory1.doSomeThing();
    }

}

package com.self.designpatterns.factory.simplefactory;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class MainTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Animal animal = simpleFactory.createAnimal(1);
        animal.voice();
    }

}

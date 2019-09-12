package com.self.designpatterns.flyweight;

/**
 * @author shichen
 * @create 2018/7/2
 * @desc
 */
public class FlyweightMainTest {

    public static void main(String[] args) {
        Flyweight flyweight = FlyweightFactory.getFlyweight("aa");
        flyweight.doOperation("bb");


        Flyweight flyweight1 = FlyweightFactory.getFlyweight("aa");
        flyweight1.doOperation("cc");
    }

}

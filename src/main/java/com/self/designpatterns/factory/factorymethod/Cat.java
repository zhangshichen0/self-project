package com.self.designpatterns.factory.factorymethod;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class Cat implements Animal {
    /**
     * 声音
     */
    @Override
    public void voice() {
        System.out.println("喵喵喵");
    }
}

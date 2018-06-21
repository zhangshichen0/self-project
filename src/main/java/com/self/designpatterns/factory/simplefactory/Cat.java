package com.self.designpatterns.factory.simplefactory;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class Cat implements Animal {
    /**
     * 叫声
     */
    @Override
    public void voice() {
        System.out.println("喵喵喵");
    }
}

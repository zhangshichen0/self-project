package com.self.designpatterns.factory.abstractFactory;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class Dog implements Animal {

    /**
     * 声音
     */
    @Override
    public void voice() {
        System.out.println("汪汪汪");
    }

}

package com.self.designpatterns.nullobject;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class NullOperation extends AbstractOperation {
    /**
     * 操作
     */
    @Override
    public void request() {
        System.out.println("do nothing");
    }
}

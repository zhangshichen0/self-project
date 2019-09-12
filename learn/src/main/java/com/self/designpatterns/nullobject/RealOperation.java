package com.self.designpatterns.nullobject;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class RealOperation extends AbstractOperation {
    /**
     * 操作
     */
    @Override
    public void request() {
        System.out.println("do some thing");
    }
}

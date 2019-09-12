package com.self.designpatterns.templatemethod;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class Tea extends AbstractBeverage {
    /**
     * 泡茶or coffer 不确定，交由子类具体操作
     */
    @Override
    protected void brew() {
        System.out.println("brew Tea");
    }

    /**
     * 增加调料
     */
    @Override
    protected void addCondiments() {
        System.out.println("Tea condiments");
    }
}

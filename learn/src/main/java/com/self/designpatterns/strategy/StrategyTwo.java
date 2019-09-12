package com.self.designpatterns.strategy;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class StrategyTwo implements Strategy {
    /**
     * 算法的具体内容
     */
    @Override
    public void behavior() {
        System.out.println("strategy two");
    }
}

package com.self.designpatterns.strategy;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 利用算法写业务逻辑
     */
    public void doSomeThing() {
        this.strategy.behavior();
    }

    /**
     * 改变算法
     * @param strategy
     */
    public void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}

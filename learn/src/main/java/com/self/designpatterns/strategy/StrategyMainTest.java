package com.self.designpatterns.strategy;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class StrategyMainTest {

    public static void main(String[] args) {

        Strategy strategy1 = new StrategyOne();
        Strategy strategy2 = new StrategyTwo();

        Context context = new Context(strategy1);
        context.doSomeThing();

        context.changeStrategy(strategy2);
        context.doSomeThing();
    }

}

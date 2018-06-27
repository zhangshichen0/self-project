package com.self.designpatterns.templatemethod;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public abstract class AbstractBeverage {

    /**
     * 准备食谱,定义为final，不可被重写
     */
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    /**
     * 烧水
     */
    protected final void boilWater() {
        System.out.println("boilWater");
    }

    /**
     * 泡茶or coffer 不确定，交由子类具体操作
     */
    protected abstract void brew();

    /**
     * 将饮品导入杯子中
     */
    protected final void pourInCup() {
        System.out.println("pourInCup");
    }

    /**
     * 增加调料
     */
    protected abstract void addCondiments();
}

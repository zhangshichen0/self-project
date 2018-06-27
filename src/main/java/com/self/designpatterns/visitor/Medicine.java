package com.self.designpatterns.visitor;

/**
 * 抽象类--element
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public abstract class Medicine {

    private String name;
    private double price;

    public Medicine(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * 定义接收访问者
     *
     * @param visitor
     */
    public abstract void accept(Visitor visitor);

}

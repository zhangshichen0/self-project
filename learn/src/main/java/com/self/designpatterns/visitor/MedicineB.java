package com.self.designpatterns.visitor;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class MedicineB extends Medicine {

    public MedicineB(String name, double price) {
        super(name, price);
    }

    /**
     * 定义接收访问者
     *
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}

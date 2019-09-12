package com.self.designpatterns.visitor;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public abstract class Visitor {

    protected String name;

    public Visitor(String name) {
        this.name = name;
    }

    /**
     * 定义访问element--medicineA的操作
     *
     * @param medicineA
     */
    public abstract void visitor(MedicineA medicineA);

    /**
     * 定义访问element--medicineB的操作
     *
     * @param medicineB
     */
    public abstract void visitor(MedicineB medicineB);
}

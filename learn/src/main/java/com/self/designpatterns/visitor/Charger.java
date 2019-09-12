package com.self.designpatterns.visitor;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class Charger extends Visitor {

    public Charger(String name) {
        super(name);
    }

    /**
     * 定义访问element--medicineA的操作
     *
     * @param medicineA
     */
    @Override
    public void visitor(MedicineA medicineA) {
        System.out.println("划价员：" + this.name + ", 给药：" + medicineA.getName() + ", 价格：" + medicineA.getPrice());
    }

    /**
     * 定义访问element--medicineB的操作
     *
     * @param medicineB
     */
    @Override
    public void visitor(MedicineB medicineB) {
        System.out.println("划价员：" + this.name + ", 给药：" + medicineB.getName() + ", 价格：" + medicineB.getPrice());
    }
}

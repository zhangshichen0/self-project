package com.self.designpatterns.visitor;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class WorkerOfPharmacy extends Visitor {

    public WorkerOfPharmacy(String name) {
        super(name);
    }

    /**
     * 定义访问element--medicineA的操作
     *
     * @param medicineA
     */
    @Override
    public void visitor(MedicineA medicineA) {
        System.out.println("药房工作者：" + this.name + ", 拿药：" + medicineA.getName());
    }

    /**
     * 定义访问element--medicineB的操作
     *
     * @param medicineB
     */
    @Override
    public void visitor(MedicineB medicineB) {
        System.out.println("药房工作者：" + this.name + ", 拿药：" + medicineB.getName());
    }
}

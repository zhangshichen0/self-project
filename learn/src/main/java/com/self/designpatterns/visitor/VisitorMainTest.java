package com.self.designpatterns.visitor;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class VisitorMainTest {

    public static void main(String[] args) {
        Presciption presciption = new Presciption();

        Medicine medicineA = new MedicineA("板蓝根", 1.0);
        Medicine medicineB = new MedicineB("感康", 2.0);

        presciption.addMedicine(medicineA);
        presciption.addMedicine(medicineB);

        Visitor charger = new Charger("李四");
        Visitor workerOfPharmacy = new WorkerOfPharmacy("张三");

        presciption.accect(charger);
        System.out.println("--------------------------------------");
        presciption.accect(workerOfPharmacy);
    }

}

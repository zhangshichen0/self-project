package com.self.designpatterns.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 药单
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class Presciption {

    private List<Medicine> medicines = null;

    public Presciption() {
        this.medicines = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        this.medicines.add(medicine);
    }

    public void removeMedicine(Medicine medicine) {
        this.medicines.remove(medicine);
    }

    /**
     * 接收访问者
     *
     * @param visitor
     */
    public void accect(Visitor visitor) {
        this.medicines.stream().forEach(medicine -> medicine.accept(visitor));
    }
}

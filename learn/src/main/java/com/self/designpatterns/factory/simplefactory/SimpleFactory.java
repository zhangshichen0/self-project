package com.self.designpatterns.factory.simplefactory;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class SimpleFactory {

    /**
     * 创建动物对象
     *
     * @param type
     * @return
     */
    public Animal createAnimal(int type) {
        Animal animal = null;
        if (type == 1) {
            animal = new Dog();
        } else if (type == 2) {
            animal = new Cat();
        }
        return animal;
    }

}

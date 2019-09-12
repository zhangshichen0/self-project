package com.self.designpatterns.composite;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class CompositeMainTest {

    public static void main(String[] args) {
        Component component = new Composite("root");

        Component leaf = new Leaf("1");
        Component leaf1 = new Leaf("2");

        component.add(leaf);
        component.add(leaf1);

        component.print();
    }

}

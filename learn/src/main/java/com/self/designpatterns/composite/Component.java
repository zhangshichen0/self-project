package com.self.designpatterns.composite;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public abstract class Component {

    protected String name;

    protected Component(String name) {
        this.name = name;
    }

    public void print() {
        print(0);
    }

    /**
     * 打印
     *
     * @param level
     */
    abstract void print(int level);

    /**
     * 增加
     *
     * @param component
     * @return
     */
    public abstract boolean add(Component component);

    /**
     * 删除
     *
     * @param component
     * @return
     */
    public abstract boolean remove(Component component);
}
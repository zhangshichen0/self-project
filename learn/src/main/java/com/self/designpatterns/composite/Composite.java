package com.self.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class Composite extends Component {

    private List<Component> components;

    public Composite(String name) {
        super(name);
        components = new ArrayList<>();
    }

    /**
     * 打印
     *
     * @param level
     */
    @Override
    void print(int level) {
        this.components.stream().forEach(component -> component.print());
    }

    /**
     * 增加
     *
     * @param component
     * @return
     */
    @Override
    public boolean add(Component component) {
        return components.add(component);
    }

    /**
     * 删除
     *
     * @param component
     * @return
     */
    @Override
    public boolean remove(Component component) {
        return components.remove(component);
    }
}

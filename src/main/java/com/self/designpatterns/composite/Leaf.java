package com.self.designpatterns.composite;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    /**
     * 打印
     *
     * @param level
     */
    @Override
    void print(int level) {
        for (int i = 0; i < level; i ++) {
            System.out.println("---");
        }

        System.out.println("left:" + name);
    }

    /**
     * 增加
     *
     * @param component
     * @return
     */
    @Override
    public boolean add(Component component) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除
     *
     * @param component
     * @return
     */
    @Override
    public boolean remove(Component component) {
        throw new UnsupportedOperationException();
    }
}

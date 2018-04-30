package com.self.proxy.dynamicproxy;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public class ZhangSan implements People {
    /**
     * 吃饭
     */
    @Override
    public void eat() {
        System.out.println("吃饭");
    }

    /**
     * 睡觉
     */
    @Override
    public void sleep() {
        System.out.println("睡觉");
    }

    /**
     * 打豆豆
     */
    @Override
    public void daDouDou() {
        System.out.println("打豆豆");
    }
}

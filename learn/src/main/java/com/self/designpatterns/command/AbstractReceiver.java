package com.self.designpatterns.command;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public abstract class AbstractReceiver {

    /**
     * 打开灯
     */
    public void lightOn() {
        System.out.println("打开灯");
    }

    /**
     * 打开灯
     */
    public void lightOff() {
        System.out.println("关闭灯");
    }
}

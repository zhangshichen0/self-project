package com.self.designpatterns.bridge;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class Tcl extends Tv {
    /**
     * 打开电视机
     */
    @Override
    public void on() {
        System.out.println("Tcl.on");
    }

    /**
     * 关闭电视机
     */
    @Override
    public void off() {
        System.out.println("Tcl.on");
    }

    /**
     * 转换频道
     */
    @Override
    public void tuneChannel() {
        System.out.println("Tcl.on");
    }
}

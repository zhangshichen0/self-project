package com.self.designpatterns.bridge;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public abstract class Tv {

    /**
     * 打开电视机
     */
    public abstract void on();

    /**
     * 关闭电视机
     */
    public abstract void off();

    /**
     * 转换频道
     */
    public abstract void tuneChannel();

}

package com.self.designpatterns.bridge;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class ConcreteRemoteControl2 extends RemoteControl {
    public ConcreteRemoteControl2(Tv tv) {
        super(tv);
    }

    /**
     * 打开电视机
     */
    @Override
    public void on() {
        this.tv.on();
        System.out.println("ConcreteRemoteControl2.on");
    }

    /**
     * 关闭电视机
     */
    @Override
    public void off() {
        this.tv.off();
        System.out.println("ConcreteRemoteControl2.off");
    }

    /**
     * 转换频道
     */
    @Override
    public void tuneChannel() {
        this.tv.tuneChannel();
        System.out.println("ConcreteRemoteControl2.tuneChannel");
    }
}

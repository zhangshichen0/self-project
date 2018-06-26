package com.self.designpatterns.command;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class LightOnCommand implements Command{

    private AbstractReceiver receiver;

    public LightOnCommand(AbstractReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 具体的执行方法
     */
    @Override
    public void execute() {
        this.receiver.lightOn();
    }
}

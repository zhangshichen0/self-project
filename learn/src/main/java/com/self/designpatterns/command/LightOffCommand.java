package com.self.designpatterns.command;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class LightOffCommand implements Command {

    private AbstractReceiver receiver;

    public LightOffCommand(AbstractReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 具体的执行方法
     */
    @Override
    public void execute() {
        receiver.lightOff();
    }
}

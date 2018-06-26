package com.self.designpatterns.command;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class Client {

    public static void main(String[] args) {
        AbstractReceiver receiver = new LightReceiver();

        Command onCommand = new LightOnCommand(receiver);
        Command offCommand = new LightOffCommand(receiver);

        Invoker invoker = new Invoker();
        invoker.registerCommand(1, onCommand);
        invoker.registerCommand(2, offCommand);


        //开灯
        invoker.execute(1);
        //关灯
        invoker.execute(2);
    }

}

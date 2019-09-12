package com.self.designpatterns.bridge;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class BridgeMainTest {

    public static void main(String[] args) {
        Tv sony = new Sony();
        RemoteControl remoteControl = new ConcreteRemoteControl1(sony);
        remoteControl.on();


        Tv tcl = new Tcl();
        RemoteControl remoteControl1 = new ConcreteRemoteControl2(tcl);
        remoteControl1.on();
    }

}

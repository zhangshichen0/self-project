package com.self.threadpool.thread;

/**
 * @author shichen
 * @create 2018/8/14
 * @desc
 */
public class InterruptThread extends Thread {

    @Override
    public void run(){
        super.run();
        for (int i=0; i < 5000; i++){
            System.out.println("i = " + (i+1));
        }
    }

}

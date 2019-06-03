package com.self.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author shichen
 * @create 2019-06-03
 * @desc
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("event:" + longEvent);
    }
}

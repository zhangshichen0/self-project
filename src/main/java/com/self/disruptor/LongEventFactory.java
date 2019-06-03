package com.self.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author shichen
 * @create 2019-06-03
 * @desc
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

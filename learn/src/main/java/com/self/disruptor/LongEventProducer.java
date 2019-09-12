package com.self.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author shichen
 * @create 2019-06-03
 * @desc
 */
public class LongEventProducer {
    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg TRANSLATOR = (EventTranslatorOneArg<LongEvent, Long>) (event, sequence, value) -> event.set(value);

    public void onData(final Long value) {
        ringBuffer.publishEvent(TRANSLATOR, value);
    }
}

package com.self.disruptor;

/**
 * @author shichen
 * @create 2019-06-03
 * @desc
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}

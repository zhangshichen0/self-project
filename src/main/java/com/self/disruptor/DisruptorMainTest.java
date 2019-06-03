package com.self.disruptor;

import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shichen
 * @create 2019-06-03
 * @desc
 */
public class DisruptorMainTest {

    public static void main(String[] args) throws InterruptedException {

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(new LongEventFactory(),
                bufferSize, new ThreadFactory() {

            private AtomicInteger id = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r,"ConsumerThread_" + id.incrementAndGet());
                return thread;
            }
        }, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());

        //多个处理器处理相同事件
        disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandler());

        //多个处理器处理不同事件 workerpool 类似线程池
        /*disruptor.handleEventsWithWorkerPool(new WorkHandler<LongEvent>() {
            @Override
            public void onEvent(LongEvent event) throws Exception {
                System.out.println("event:" + event);
                event.set(event.get() + 10);
            }
        }).thenHandleEventsWithWorkerPool(new WorkHandler<LongEvent>() {
            @Override
            public void onEvent(LongEvent event) throws Exception {
                event.set(event.get() + 11);
                System.out.println("event1:" + event);
            }
        });*/
//        disruptor.handleEventsWith(new MyEventHandler()).then(new MyEventHandler());  //Pipeline
        RingBuffer<LongEvent> ringBuffer = disruptor.start();

        LongEventProducer producer = new LongEventProducer(ringBuffer);
        for (long i = 0; i < 10; i++) {
            producer.onData(i);
            // wait for task execute....
            Thread.sleep(1000);
        }

        disruptor.shutdown();
    }
    
}

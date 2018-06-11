package com.self.lock;

/**
 * @author shichen
 * @create 2018/6/11
 * @desc
 */
public class TestSimpleLinkedBlockQueue {

    SimpleLinkedBlockQueue<String> stringSimpleLinkedBlockQueue = new SimpleLinkedBlockQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        TestSimpleLinkedBlockQueue testSimpleLinkedBlockQueue = new TestSimpleLinkedBlockQueue();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i ++){
                    try {
                        testSimpleLinkedBlockQueue.stringSimpleLinkedBlockQueue.put(String.valueOf(i));
                        System.out.println(Thread.currentThread().getId() + "   " + System.currentTimeMillis() + ", i=" + i + ",put queue");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i ++){
                    try {
                        String x = testSimpleLinkedBlockQueue.stringSimpleLinkedBlockQueue.take();
                        System.out.println(Thread.currentThread().getId() + "   " + System.currentTimeMillis() + ", x=" + x + ",take queue");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i ++){
                    try {
                        String x = testSimpleLinkedBlockQueue.stringSimpleLinkedBlockQueue.take();
                        System.out.println(Thread.currentThread().getId() + "   " + System.currentTimeMillis() + ", x=" + x + ",take queue");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        producer.start();
        consumer.start();
        consumer2.start();

        Thread.currentThread().join();
    }

}

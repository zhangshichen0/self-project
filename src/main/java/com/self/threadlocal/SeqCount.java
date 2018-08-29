package com.self.threadlocal;

/**
 * 每一个线程中包含一个ThreadLocal.ThreadLocalMap对象，使用Entry保存key【ThreadLocal对象】与value，entry对象保存在Entry数组中
 *
 * @author shichen
 * @create 2018/8/29
 * @desc
 */
public class SeqCount {

    /**
     * 所有的线程共享一个ThreadLocal对象
     */
    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>(){
        // 实现initialValue()
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    public int nextSeq(){
        seqCount.set(seqCount.get() + 1);

        return seqCount.get();
    }

    public static void main(String[] args){
        SeqCount seqCount = new SeqCount();

        SeqThread thread1 = new SeqThread(seqCount);
        SeqThread thread2 = new SeqThread(seqCount);
        SeqThread thread3 = new SeqThread(seqCount);
        SeqThread thread4 = new SeqThread(seqCount);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private static class SeqThread extends Thread{
        private SeqCount seqCount;

        SeqThread(SeqCount seqCount){
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for(int i = 0 ; i < 1 ; i++){
                System.out.println(Thread.currentThread().getName() + " seqCount :" + seqCount.nextSeq());
            }
        }
    }

}

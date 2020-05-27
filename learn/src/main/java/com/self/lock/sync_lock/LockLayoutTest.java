package com.self.lock.sync_lock;

import org.ehcache.impl.store.HashUtils;
import org.openjdk.jol.info.ClassLayout;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 使用jol对对象头进行分析，分析锁升级过程
 * https://blog.csdn.net/qq_27695659/article/details/104390088
 *
 * @author shichen
 * @create 2020/5/27
 * @desc
 */
public class LockLayoutTest {

    private final static Object a = new Object();

    public static void main(String[] args) throws InterruptedException, IOException {
        //睡眠5s则，可以看到偏向锁，因为jvm在启动的时候会延迟偏向，所以在未加睡眠的时候，会直接加轻量级锁
        //TimeUnit.SECONDS.sleep(5);
        //调用hashCode后没有偏向锁了
        a.hashCode();
        /*Thread t1= new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    try {
                        Thread.sleep(5000);
                        System.out.println("t1 release");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(1000);*/
        System.out.println("t1 lock ing");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());//轻量锁
        //此时t1线程把a锁住，并未释放。但是sync主线程这个时候过来竞争啊这把锁。所以锁会升级为重量锁
        sync();
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());//重量锁

        System.gc();
        System.out.println("after gc()");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());//无锁---gc

        sync();
    }

    public  static  void sync() throws InterruptedException {
        synchronized (a){
            System.out.println("t1 main lock");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());//重量锁
        }
    }

    /**
     * 确定哪几位是hashcode位
     */
    public static void hashcodeBytes() {
        //查看对象对象头中hashcode
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        System.out.println(Integer.toHexString(object.hashCode()));
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    /**
     * 查看cpu+操作系统是大端还是小端存储
     *
     * https://www.jianshu.com/p/bb1b882d8d61
     *
     * @throws IOException
     */
    public static void seeBigOrLittleCpu() throws IOException {
        int a = 0x12345678;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(a);
        byte[] b = baos.toByteArray();
        for(int i = 0;i<4;i++){
            System.out.println(Integer.toHexString(b[i]));
        }
    }

}

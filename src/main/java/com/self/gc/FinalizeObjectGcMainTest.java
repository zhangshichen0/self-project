package com.self.gc;

import java.util.concurrent.TimeUnit;

/**
 * 验证重写了finalize方法的对象回收
 *
 * @author shichen
 * @create 2019-08-02
 * @desc
 */
public class FinalizeObjectGcMainTest {

    private static FinalizeObjectGcMainTest finalizeObjectGcMainTest;

    private byte[] bytes;

    public FinalizeObjectGcMainTest() {

        this.bytes = new byte[10 * 1024 * 1024];

    }

    public static void main(String[] args) throws InterruptedException {
        FinalizeObjectGcMainTest.finalizeObjectGcMainTest = new FinalizeObjectGcMainTest();

        FinalizeObjectGcMainTest.finalizeObjectGcMainTest = null;

        //并不能对finalizeObjectGcMainTest申请的内存空间进行回收
        System.gc();

        TimeUnit.SECONDS.sleep(10);

        FinalizeObjectGcMainTest.finalizeObjectGcMainTest = null;

        //能够回收
        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        FinalizeObjectGcMainTest.finalizeObjectGcMainTest = this;
    }
}

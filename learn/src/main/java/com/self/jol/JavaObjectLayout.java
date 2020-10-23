package com.self.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * java对象布局--在mac上需要授权
 *
 * @author shichen
 * @create 2019-11-07
 * @desc
 */
public class JavaObjectLayout {

    public static void main(String[] args) throws InterruptedException {

        //为了启动偏向锁
        TimeUnit.SECONDS.sleep(10);
        Object object = new Object();

        System.out.println(ClassLayout.parseInstance(object).toPrintable());

    }

}

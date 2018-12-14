package com.self.spi;

import java.util.Iterator;

/**
 * @author shichen
 * @create 2018-12-14
 * @desc
 */
public class SpiTestMain {

    public static void main(String[] args) {

        //使用第一个实现
        SpiDemoService spiDemoService = ServiceBootstrap.loadFirst(SpiDemoService.class);
        System.out.println(spiDemoService.getDemo());

        //获取所有该类的实现
        Iterator<SpiDemoService> iterator = ServiceBootstrap.loadAll(SpiDemoService.class);
        while (iterator.hasNext()) {
            SpiDemoService spiDemoService1 = iterator.next();
            System.out.println(spiDemoService1.getDemo());
        }
    }

}

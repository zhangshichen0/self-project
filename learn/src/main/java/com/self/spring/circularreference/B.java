package com.self.spring.circularreference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shichen
 * @create 2019-10-17
 * @desc
 */
@Service
public class B implements BB {

    @Autowired
    private AA aa;

    @Override
    public void b() {
        System.out.println("B.b");
        System.out.println("B" + Thread.currentThread().getName());
        //aa.a();
    }

    @Override
    public void c() {
        System.out.println("b:c");
    }
}

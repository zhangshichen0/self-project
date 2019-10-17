package com.self.spring.circularreference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shichen
 * @create 2019-10-17
 * @desc
 */
@Service
public class A implements AA {;


    @Lazy
    @Autowired
    private BB bb;

    @Async
    @Override
    public void a() {
        System.out.println("A.a");
    }

}

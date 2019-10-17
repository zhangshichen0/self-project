package com.self.spring.circularreference;

import org.springframework.beans.factory.annotation.Autowired;
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
        aa.a();
    }
}

package com.self.spring.circularreference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shichen
 * @create 2019-10-18
 * @desc
 */
@Service
public class C implements CC {

    @Autowired
    private BB bb;

    @Override
    public void cc() {
        System.out.println("c" + bb);
    }
}

package com.self.spring.aop.simpleAop.container;

import java.lang.annotation.*;

/**
 * @author shichen
 * @create 2018/9/19
 * @desc
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Instance {
}

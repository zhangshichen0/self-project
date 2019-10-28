package com.self.spring.factorybean;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MapperRegistrar.class)
@Documented
public @interface Mapper {

    String value() default "";

}

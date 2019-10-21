package com.self.spring.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author shichen
 * @create 2019-10-21
 * @desc
 */
public class HelloEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public HelloEvent(Object source) {
        super(source);
    }
}

package com.self.designpatterns.observer;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public interface Observer {

    /**
     * 更新
     * @param humidity
     * @param pressure
     * @param temp
     */
    void update(float temp, float humidity, float pressure);

}

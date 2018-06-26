package com.self.designpatterns.observer;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class StatisticsDisplay implements Observer {

    /**
     * 注册
     *
     * @param subject
     */
    public StatisticsDisplay(Subject subject) {
        subject.registerObserver(this);
    }

    /**
     * 更新
     *
     * @param temp
     * @param humidity
     * @param pressure
     */
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("StatisticsDisplay.update: " + temp + " " + humidity + " " + pressure);
    }
}

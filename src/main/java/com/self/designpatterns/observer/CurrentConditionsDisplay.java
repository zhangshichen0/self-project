package com.self.designpatterns.observer;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class CurrentConditionsDisplay implements Observer {

    public CurrentConditionsDisplay(Subject subject) {
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
        System.out.println("CurrentConditionsDisplay.update: " + temp + " " + humidity + " " + pressure);
    }
}

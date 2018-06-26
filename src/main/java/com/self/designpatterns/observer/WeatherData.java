package com.self.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 天气变化
 *
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class WeatherData implements Subject {

    private final List<Observer> observers;

    private float temperature;
    private float humidity;
    private float pressure;


    public WeatherData() {
        super();
        observers = new ArrayList<>();
    }

    /**
     * 数据发生变化
     *
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        //通知所有观察者
        notifyAllObservers();
    }

    /**
     * 注册观察者
     *
     * @param observer
     */
    @Override
    public void registerObserver(Observer observer) {
        if (Objects.nonNull(observer)) {
            observers.add(observer);
        }
    }

    /**
     * 移除观察者
     *
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        int position = observers.indexOf(observer);
        if (position >= 0) {
            observers.remove(position);
        }
    }

    /**
     * 通知所有注册的观察者
     */
    @Override
    public void notifyAllObservers() {
        if (!observers.isEmpty()) {
            observers.stream().forEach(observer -> observer.update(temperature, humidity, pressure));
        }
    }
}

package com.self.designpatterns.observer;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class ObserverMainTest {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        new StatisticsDisplay(weatherData);
        new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(0, 0, 0);
        weatherData.setMeasurements(1, 1, 1);
    }

}

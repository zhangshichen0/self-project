package com.self.designpatterns.observer;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public interface Subject {

    /**
     * 注册观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有注册的观察者
     */
    void notifyAllObservers();

}

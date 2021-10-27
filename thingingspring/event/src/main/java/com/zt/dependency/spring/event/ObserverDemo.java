package com.zt.dependency.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} 示例
 *
 * @Author: Tommy
 * @DATE: 2021/10/27
 * @see Observer
 * @since
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        // 添加观察者（监听者）
        observable.addObserver(new EventObserver());

        observable.notifyObservers("Hello,World");
    }

    static class EventObservable extends Observable {
        @Override
        public void setChanged(){
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg){
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        /**
         *
         * @param o  发布者
         * @param event  关联的数据
         */
        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("收到事件 : " + eventObject);
        }
    }
}

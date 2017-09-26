package com.cc.event;

import java.util.*;

/**
 * Created by wanchao on 2017/8/4.
 */
public class ButtonDemo {
    private String msg;
    private Set<EventListener> eventListeners;

    public ButtonDemo(String msg) {
        this.msg = msg;
        eventListeners = new HashSet<>();
    }

    private void addListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public void btnClick(){
        notice();
    }

    private void notice(){
        Iterator<EventListener> iterator = eventListeners.iterator();
        while (iterator.hasNext()) {
            ButtonClickListenerInner next = (ButtonClickListenerInner)iterator.next();
            next.btnClocked(new ButtonClickEvent(this));
        }
    }

    public String getMsg() {
        return msg;
    }

    public static void main(String[] args) {
        ButtonDemo demo = new ButtonDemo("test");
        demo.addListener(new ButtonClickListenerInner());
        demo.btnClick();
    }

}

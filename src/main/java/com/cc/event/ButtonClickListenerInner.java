package com.cc.event;

import java.util.EventListener;

/**
 * Created by wanchao on 2017/8/4.
 */
public class ButtonClickListenerInner implements EventListener {
    public void btnClocked(ButtonClickEvent event) {
        ButtonDemo source = (ButtonDemo) event.getSource();
        System.out.println("-----" + source.getMsg());
    }
}

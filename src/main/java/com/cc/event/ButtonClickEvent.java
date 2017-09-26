package com.cc.event;

import java.util.EventObject;

/**
 * Created by wanchao on 2017/8/4.
 */
public class ButtonClickEvent extends EventObject {

    private Object mSourceObject =null;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ButtonClickEvent(Object source) {
        super(source);
        mSourceObject = source;
    }

    @Override
    public Object getSource() {
        return mSourceObject;
    }

    public void setmSourceObject(Object mSourceObject) {
        this.mSourceObject = mSourceObject;
    }
}

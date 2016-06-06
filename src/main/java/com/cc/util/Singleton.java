package com.cc.util;

import sun.security.jca.GetInstance;

/**
 * Created by chengwanchao on 2016/6/6.
 */
public enum Singleton {
    INSTANCE(1);
    private int i;
    Singleton(int i){
        this.i = i;
    }
    public Singleton GetInstance(){
        return INSTANCE;
    }
}

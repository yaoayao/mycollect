package com.cc.constants;

/**
 * Created by chengwanchao on 2016/6/3.
 */
public interface ConstantsInterface {
    public String KEY ="key";
    interface ConSub{
        String KEY ="sub";
    }

    //调用
    /*String s = ConstantsInterface.KEY;
    String t = ConstantsInterface.ConSub.KEY;*/
}

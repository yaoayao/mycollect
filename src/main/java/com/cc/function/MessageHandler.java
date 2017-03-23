package com.cc.function;

import java.util.function.Function;

/**
 * Created by miyan(弥彦) on 2017/2/14 11:19.
 */
public class MessageHandler {
    Function handleMsg(Function getString){
        return getString;
    }
    String getOne(){
        return "1";
    }

}

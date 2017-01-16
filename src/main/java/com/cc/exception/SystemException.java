package com.cc.exception;

/**
 * Created by miyan(弥彦) on 2017/1/16 10:37.
 */
public abstract class SystemException extends RuntimeException{

    private static final long serialVersionUID = 606025621416892266L;

    protected SystemException(String message , Object...objects){
        super(String.format(message , objects));
    }
    protected SystemException(String message ,Exception e, Object...objects){
        super(String.format(message , objects) ,e);
    }
    protected SystemException(Exception e){
        super(e.getMessage() , e);
    }
}

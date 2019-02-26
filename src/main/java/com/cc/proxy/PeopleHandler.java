package com.cc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PeopleHandler implements InvocationHandler {
    private Object object;

    public PeopleHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(this.object, args);
        System.out.println("代理呢");
        return invoke;
    }
}

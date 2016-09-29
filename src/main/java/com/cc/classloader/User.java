package com.cc.classloader;

/**
 * Created by chengwanchao on 2016/9/28.
 */
public class User {
    private User user;
    public void setUser(Object o){
        System.out.println("00000");
        this.user = (User) o;
    }

}

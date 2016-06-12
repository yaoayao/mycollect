package com.cc.po;

/**
 * Created by chengwanchao on 2016/6/9.
 */
public class Person implements Cloneable{
    private String name;
    private User user;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person p =null;
        p = (Person) super.clone();
        return p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

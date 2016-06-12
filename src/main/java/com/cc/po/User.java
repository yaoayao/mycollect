package com.cc.po;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengwanchao on 2016/6/3.
 */
public class User implements Cloneable{

    @Override
    public Object clone() throws CloneNotSupportedException {
        User clone = (User)super.clone();
        return clone;
    }

    private Integer userId;
    private String userName;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private Person person;

    /*private Map<String, List<String>> stringList;

    public Map<String, List<String>> getStringList() {
        return stringList;
    }

    public void setStringList(Map<String, List<String>> stringList) {
        this.stringList = stringList;
    }*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

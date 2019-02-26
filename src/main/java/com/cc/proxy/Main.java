package com.cc.proxy;

import com.cc.po.Person;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        PeopleHandler peopleHandler = new PeopleHandler(person);
        Person person1 = (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(), new Class[] {Person.class}, peopleHandler);
//        Proxy.newProxyInstance()
        person1.getName();
    }
}

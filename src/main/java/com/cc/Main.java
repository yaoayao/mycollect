package com.cc;

import com.alibaba.fastjson.JSON;
import com.cc.po.Person;
import com.cc.po.User;
import com.cc.util.JacksonJsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengwanchao on 2016/6/3.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {

        //Dozer对象拷贝实例
        /*User user = new User();
        user.setUserId(111);
        user.setUserName("2016-02-10");

        UserVo userVo = new UserVo();
        DozerBeanMapper mapper  = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer.xml"));
        userVo = mapper.map(user,UserVo.class);
        System.out.println(userVo.getUserId());
        System.out.println(userVo.getUserNameVo());*/


        /*Thread thread = new Thread(){
            public void run(){
                System.out.println("-------------thread----------");
            }
        };
        thread.start();*/

        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("---------------runnable---------");

            }
        };
        Thread t = new Thread(runnable);
        t.run();//start()
        System.out.println("main-------------");*/

     /*   MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();
        thread1.join(); //wait for thread 1 to terminate
        thread2.join();

    }
    public static class MyRunnable implements Runnable {
        private Integer threadLocal =
                new Integer(0);
        @Override
        public void run() {
            synchronized(this){
                //threadLocal.set( (int) (Math.random() * 100D) );
                threadLocal=(int) (Math.random() * 100D);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(threadLocal);
        }
    }*/



//            int number = -10;
//            //原始数二进制
//            printInfo(number);
//            //number = number << 1;
//            //左移一位
//            //printInfo(number);
//            number = number >> 1;
//            //右移一位
//            printInfo(number);
        /*List<String> ls = new ArrayList<String>();
        ls.add("9999");
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        map.put("qqq",ls);
        map.put("www",ls);
        User user = new User();
        user.setUserName("aaa");
        user.setUserId(11);
        user.setStringList(map);
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user);
        list.add(user);

        String s = "[{\"stringList\":{\"qqq\":\"1111\",\"www\":\"222\"},\"userId\":11,\"userName\":\"aaa\"},{\"$ref\":\"$[2]\"},{\"$ref\":\"$[2]\"}]";
        List user1 = (List) JacksonJsonUtil.json2Object(s, List.class);*/

        Person p = new Person();
        p.setName("pppp");
        User u = new User();
        u.setUserName("uuuu");
        p.setUser(u);

        Person p1 = new Person();
        p1 = (Person) p.clone();
        p1.setName("------");
        p.getUser().setUserName("2222");
        System.out.println(p.getName());
        System.out.println(p1.getName());



        Gson g = new Gson();
//        System.out.println(JacksonJsonUtil.object2Json(p));


    }


//    private static void printInfo(int num){
//        System.out.println(Integer.toBinaryString(num));
//    }
}

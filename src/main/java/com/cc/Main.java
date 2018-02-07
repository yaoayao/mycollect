package com.cc;

import com.cc.po.User;
import com.cc.util.SerializeUtil;
import com.cc.util.StringTools;
import com.cc.util.UnicodeUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chengwanchao on 2016/6/3.
 */
public class Main {
    public static void main(String[] args) throws Exception {

//        String messageStr = "0003043818483635303637303236313635363733343630303431313038353036313538";
//        byte[] bytes = StringTools.hexString2Byte(messageStr);
//        byte[] ver = new byte[4];
//        byte[] deviceType = new byte[1];
//        byte[] imei = new byte[15];
//        byte[] imsi = new byte[15];
//
//        System.arraycopy(bytes, 0, ver, 0, 4);
//        System.arraycopy(bytes, 4, deviceType, 0, 1);
//        System.arraycopy(bytes, 5, imei, 0, 15);
//        System.arraycopy(bytes, 20, imsi, 0, 15);
//        System.out.println(new String(imei));
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

        /*Person p = new Person();
        p.setName("pppp");
        User u = new User();
        u.setUserName("uuuu");
        p.setUser(u);

        Person p1 = new Person();
        p1 = (Person) p.clone();
        p1.setName("------");
        p.getUser().setUserName("2222");
        System.out.println(p.getName());
        System.out.println(p1.getName());*/
//        AtomicInteger ai = new AtomicInteger();
//        System.out.println(ai);
//        System.out.println(ai.getAndIncrement());
//        System.out.println(ai);
//
////        System.out.println(JacksonJsonUtil.object2Json(p));
//        String str1 = new StringBuilder("计算机").append("软件").toString();
//        System.out.println(str1.intern() == str1);
//        String str2 = new StringBuilder("ja").append("va").toString();
//        System.out.println(str2.intern() == str2);
//        for(int i = 0 ;i < 20 ;i ++) {
//            Thread thread = new Thread(() -> {
//                System.out.println("新线程开始");
//                try {
//                    Thread.sleep(1000 * 200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("线程结束");
//            });
//            thread.setName("myThread" + i);
//            thread.start();
//        }

    }

//    private static void printInfo(int num){
//        System.out.println(Integer.toBinaryString(num));
//    }
}

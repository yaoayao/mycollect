package com.cc;

import com.cc.util.Singleton;

/**
 * Created by chengwanchao on 2016/6/3.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

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

        Singleton Singleton = com.cc.util.Singleton.INSTANCE.GetInstance();
    }
}

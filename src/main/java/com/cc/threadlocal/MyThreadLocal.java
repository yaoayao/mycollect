package com.cc.threadlocal;

/**
 * Created by wanchao on 2018/3/28.
 */
public class MyThreadLocal {
    static final ThreadLocal threadLocal=new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return "xiezhaodong";
        }
    };
    static final InheritableThreadLocal threadLocal1=new InheritableThreadLocal(){
        @Override
        protected Object initialValue() {
            return "xiezhaodong";
        }
    };
    public static void main(String[] args) {
        threadLocal1.set("hahahah");
        System.out.println(threadLocal1.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal1.get());
            }
        }).start();
    }
    static class MyThread implements Runnable{
        private ThreadLocal<String> threadLocal;

        public MyThread(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            System.out.println(threadLocal.get());
        }
    }




}

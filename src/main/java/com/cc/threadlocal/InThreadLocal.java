package com.cc.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanchao on 2018/3/29.
 */
public class InThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        System.out.println(inheritableThreadLocal.get());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
                inheritableThreadLocal.get();
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                inheritableThreadLocal.get();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========");
        Span span = inheritableThreadLocal.get();
    }

static class Span {
    public String name;
    public int age;
    public Span(String name) {
        this.name = name;
    }
}
}

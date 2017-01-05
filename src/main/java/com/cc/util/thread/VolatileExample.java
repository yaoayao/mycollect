package com.cc.util.thread;

/**
 * Created by miyan(弥彦) on 2017/1/5 14:57.
 */
public class VolatileExample {
    int x = 0;
    volatile int b = 0;
    private void write() {
        x = 5;
        b = 1;
    }
    private void read() {
        int dummy = b;
        while (x!=5) {
        }
    }
    public static void main(String[] args) throws Exception {
        final VolatileExample example = new VolatileExample();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                example.write();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                example.read();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(example.x + "---" + example.b);

    }
//    x并不需要定义为volatile, 程序里可以有需要类似x的变量，我们只需要一个volatile变量b来确保线程2能看到线程1对x的修改：
//
//    根据代码顺序规则，线程1的x=5; happens-before b=1;; 线程2的int dummy = b; happens-before while(x!=5);
//    根据volatile变量规则，线程2的b=1; happens-before int dummy=b;
//    根据传递性，x=5; happens-before while(x!=5);
}

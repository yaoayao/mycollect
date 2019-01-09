package com.cc.communication;

/**
 * 生产者 消费者
 */
public class PAndCMain {

    public boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        PAndCMain pAndCMain = new PAndCMain();
        Consumer consumer = new Consumer(pAndCMain);
        Produce produce = new Produce(pAndCMain);
        consumer.start();
        Thread.sleep(5000);
        produce.start();
    }
}

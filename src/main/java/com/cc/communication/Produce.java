package com.cc.communication;

/**
 * 生产者
 */
public class Produce extends Thread {
    private PAndCMain pAndCMain;

    public Produce(PAndCMain pAndCMain) {
        this.pAndCMain = pAndCMain;
    }

    @Override
    public void run() {
        synchronized (PAndCMain.class){
            System.out.println("生产数据");
            pAndCMain.flag = true;
            PAndCMain.class.notify();
        }
    }
}

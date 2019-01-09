package com.cc.communication;

/**
 * 奇数线程
 */
public class OddThread extends Thread {
    private TwoThreadWaitNotify twoThreadWaitNotify;

    public OddThread(TwoThreadWaitNotify twoThreadWaitNotify) {
        this.twoThreadWaitNotify = twoThreadWaitNotify;
    }

    @Override
    public void run() {
        synchronized (TwoThreadWaitNotify.class){
            while (twoThreadWaitNotify.getStart() <100){
                if (!twoThreadWaitNotify.isFlag()){
                    System.out.println(Thread.currentThread() + " 打印奇数：" + twoThreadWaitNotify.getStart());
                    twoThreadWaitNotify.setStart(twoThreadWaitNotify.getStart() +1);
                    twoThreadWaitNotify.setFlag(true);
                    try {
                        TwoThreadWaitNotify.class.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        TwoThreadWaitNotify.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

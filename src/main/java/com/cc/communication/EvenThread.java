package com.cc.communication;

/**
 * 偶数
 */
public class EvenThread extends Thread {

    private TwoThreadWaitNotify twoThreadWaitNotify;
    public EvenThread(TwoThreadWaitNotify twoThreadWaitNotify) {
        this.twoThreadWaitNotify = twoThreadWaitNotify;
    }

    @Override
    public void run() {
        synchronized (TwoThreadWaitNotify.class){
//            System.out.println("偶数");
            while (this.twoThreadWaitNotify.getStart() <= 100 ){
                if (this.twoThreadWaitNotify.isFlag()){
                    System.out.println(Thread.currentThread() + " 打印偶数：" + this.twoThreadWaitNotify.getStart());
                    twoThreadWaitNotify.setStart(twoThreadWaitNotify.getStart() +1);
                    this.twoThreadWaitNotify.setFlag(false);
                    try {
                        TwoThreadWaitNotify.class.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        TwoThreadWaitNotify.class.wait();
                    } catch (InterruptedException e) {


                    }
                }
            }
        }
        super.run();
    }
}

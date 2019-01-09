package com.cc.communication;

public class Consumer extends Thread {
    private PAndCMain pAndCMain;

    public Consumer(PAndCMain pAndCMain) {
        this.pAndCMain = pAndCMain;
    }

    @Override
    public void run() {
        synchronized (PAndCMain.class){
            while (!pAndCMain.flag){
                System.out.println("没有数据");
                try {
                    PAndCMain.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费数据");
        }
    }
}

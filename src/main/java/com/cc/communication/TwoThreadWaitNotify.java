package com.cc.communication;

public class TwoThreadWaitNotify {
    private int start = 1;
    private boolean flag = false;

    public static void main(String[] args) {
        TwoThreadWaitNotify twoThreadWaitNotify = new TwoThreadWaitNotify();
        EvenThread evenThread = new EvenThread(twoThreadWaitNotify);
        OddThread oddThread = new OddThread(twoThreadWaitNotify);
        oddThread.start();
        evenThread.start();

    }

    public int getStart() {
        return start;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setStart(int start) {
        this.start = start;
    }
}

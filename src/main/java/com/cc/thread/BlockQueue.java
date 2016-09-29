package com.cc.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by chengwanchao on 2016/9/26.
 */
public class BlockQueue {
    public Object notEmpty;
    private Queue<Object> queue = new LinkedList<Object>();
    public Object take() throws InterruptedException {
        if (queue.size() == 0){
            notEmpty.wait();
        }
        return queue.poll();
    }

    public void put(Object o){
        if (queue.size()==0){
            notEmpty.notifyAll();
        }
        queue.add(o);
    }
}

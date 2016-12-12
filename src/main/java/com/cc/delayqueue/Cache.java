package com.cc.delayqueue;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaoyao on 2016/12/12.
 * DelayQueue实现缓存，守护线程读取DelayQueue第一个元素，然后删除map中数据
 */
public class Cache<K,V> {
    private ConcurrentMap<K,V> map = new ConcurrentHashMap<K,V>();
    private DelayQueue<DelayItem<Pair<K,V>>> q = new DelayQueue<DelayItem<Pair<K, V>>>();
    private Thread thread;
    private Cache(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                check();
            }
        };
        thread = new Thread(r);
        thread.setDaemon(true);
        thread.setName("cache daemon");
        thread.start();
    }
    private void check() {
        for (;;){
            DelayItem<Pair<K, V>> delayItem = null;
            try {
                //此处会阻塞 直到时间到期
                delayItem = q.take();
            } catch (InterruptedException e) {
                break;
            }
            if (delayItem != null){
                Pair<K, V> pair = delayItem.getItem();
                map.remove(pair.first,pair.second);
            }
        }
    }

    public void put(K key,V value,long time,TimeUnit unit){
        V old = map.put(key, value);
        if (old != null){
            q.remove(old);
        }
        long nanoTime = TimeUnit.NANOSECONDS.convert(time,unit);
        q.put(new DelayItem<Pair<K, V>>(new Pair<K, V>(key,value),nanoTime));
    }

    public V get(K key){
        return map.get(key);
    }

    public static void main(String[] args) throws InterruptedException {
        Cache<Integer,String> cache = new Cache<Integer, String>();
        cache.put(1,"aaa",3,TimeUnit.MINUTES);

        cache.put(3,"333",5,TimeUnit.MINUTES);
        cache.put(2,"222",4,TimeUnit.MINUTES);
        cache.put(4,"444",6,TimeUnit.MINUTES);
        Thread.sleep(2000);
        Map<String ,String> a = new HashMap<String, String>();
        a.put("111","1111");
        System.out.println(JSON.toJSONString(a));

        Thread.sleep(2000);
        System.out.println(JSON.toJSON(cache));
    }
}

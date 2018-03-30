package com.cc.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by wanchao on 2018/3/30.
 */
public class LoopMap {
    public static void main(String[] args) {
        //遍历map效率最高
        Map<String,String> map = new HashMap();
        map.put("key", "value");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }

    }
}

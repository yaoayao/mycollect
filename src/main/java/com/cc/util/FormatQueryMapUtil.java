package com.cc.util;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by chengwanchao on 2016/6/9.
 */
public class FormatQueryMapUtil {
    public static String FormatQueryParaMap(HashMap<String, String> parameters)
            throws RuntimeException {

        String buff = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                    parameters.entrySet());

            Collections.sort(infoIds,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Map.Entry<String, String> o1,
                                           Map.Entry<String, String> o2) {
                            return (o1.getKey()).toString().compareTo(
                                    o2.getKey());
                        }
                    });

            for (int i = 0; i < infoIds.size(); i++) {
                Map.Entry<String, String> item = infoIds.get(i);
                if (item.getKey() != "") {
                    buff += item.getKey() + "="
                            + URLEncoder.encode(item.getValue(), "GBK").replace("+", "%20") + "&";
                }
            }
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return buff;
    }

    public static String FormatBizQueryParaMap(HashMap<String, String> paraMap,
                                               boolean urlencode) throws RuntimeException {

        String buff = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                    paraMap.entrySet());

            //排序
            Collections.sort(infoIds,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Map.Entry<String, String> o1,
                                           Map.Entry<String, String> o2) {
                            return (o1.getKey()).toString().compareTo(
                                    o2.getKey());
                        }
                    });

            for (int i = 0; i < infoIds.size(); i++) {
                Map.Entry<String, String> item = infoIds.get(i);
                //System.out.println(item.getKey());
                if (item.getKey() != "") {

                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlencode) {
                        val = URLEncoder.encode(val, "GBK").replace("+", "%20");
                    }
                    buff += key.toLowerCase() + "=" + val + "&";

                }
            }

            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return buff;
    }

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("c","3+");
        map.put("a","1");
        map.put("b","2");

        System.out.println(FormatBizQueryParaMap(map,false));
    }
}

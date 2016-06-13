package com.cc.util;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.io.*;

/**
 * Created by chengwanchao on 2016/6/12.
 */
public class SerializeUtil {
    private static Log log = LogFactory.getLog(SerializeUtil.class);


    public static byte[] serialize(Object o){
        byte[] b = null;
        if (o == null) return null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try{
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(o);
            b = byteArrayOutputStream.toByteArray();
            return b;
        }catch (Exception e){
            log.error("序列化出错，error:{}", e);
        }finally {

            if (objectOutputStream!=null) try {
                objectOutputStream.close();
                if (byteArrayOutputStream!=null) byteArrayOutputStream.close();

            } catch (IOException e) {
                log.error("关闭流对象出错，error：{}", e);
            }
        }
        return b;
    }

    public static Object unSerialize(byte[] b){
        Object o = null;
        if (b == null) return o;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(b);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            o = objectInputStream.readObject();
        }catch (Exception e){
            log.error("反序列化出错。error：{}", e);

        }finally {
            try{
                if (byteArrayInputStream != null) byteArrayInputStream.close();
                if (objectInputStream!= null) objectInputStream.close();
            }catch (Exception e){
                log.error("反序列化关闭流对象出错，error：{}", e);
            }
        }

        return o;
    }
}

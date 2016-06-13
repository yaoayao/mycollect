package com.cc.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * ���������
 *
 * @author lizhenzong
 * @version 1.0
 * @since 2014-09-18
 */
public class ObjectUtil {
    private static Log log = LogFactory.getLog(ObjectUtil.class);

    /**
     * ���ƶ���
     */
    public static Object cloneObject(Object obj) {
        Object newObj = null;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(obj);
            out.close();

            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            newObj = in.readObject();
            in.close();
        } catch (Exception e) {
            log.info("��ȸ��Ƴ����쳣..." + e.getMessage(), e);
        }
        return newObj;
    }

    /**
     * ��Objectת����Map<String,Object>
     */
    public static Map<String, Object> converObjectToMap(Object obj) throws Exception {
        Map<String, Object> bizMap = new HashMap<String, Object>();
        Class cls = obj.getClass();
        buildMapFromObj(bizMap, obj, cls);
        return bizMap;
    }

    /**
     * ��Objectת����Map<String,Object>
     */
    public static Map<String, Object> converObjectToTreeMap(Object obj) throws Exception {
        Map<String, Object> bizMap = new TreeMap<String, Object>();
        Class cls = obj.getClass();
        buildMapFromObj(bizMap, obj, cls);
        return bizMap;
    }

    /**
     * ��Map<Object,Object>ת��Object
     */
    public static Object converMapToObject(Map<Object, Object> map, Class c) {
        Object obj = null;
        try {
            if (map != null) {
                obj = c.newInstance();
                // �������������
                buildObjFromMap(map, c, obj);
            }
        } catch (Exception e) {
            log.error("converMapToObject occur Exception:" + e.getMessage(), e);
        }
        return obj;
    }

    /**
     * Objectת���ɼ���
     */
    private static void buildMapFromObj(Map<String, Object> bizMap, Object obj, Class c) {
        try {
            Method[] methods = c.getDeclaredMethods();
            Field[] fields = c.getDeclaredFields();
            for (int a = 0; a < fields.length; a++) {
                Field field = fields[a];
                String filedName = field.getName();
                for (int b = 0; b < methods.length; b++) {
                    Method method = methods[b];
                    String methodsName = method.getName();
                    if (methodsName.equalsIgnoreCase("get" + filedName) || methodsName.equalsIgnoreCase("is" + filedName)) {
                        Object result = method.invoke(obj, null);
                        if (result != null) {
                            bizMap.put(filedName, result);
                        }
                    }
                }
            }
            // ����и��࣬���õݹ飬��ȡ���������
            if (c.getGenericSuperclass() != null) {
                Class superClass = c.getSuperclass();
                buildMapFromObj(bizMap, obj, superClass);
            }
        } catch (Exception e) {
            log.error("buildMapFromObj occur Exception:" + e.getMessage(), e);
        }
    }

    /**
     * ����ת����Object
     */
    private static void buildObjFromMap(Map<Object, Object> map, Class c, Object obj) {
        try {
            Method[] methods = c.getDeclaredMethods();
            Field[] fields = c.getDeclaredFields();
            for (int a = 0; a < fields.length; a++) {
                Field field = fields[a];
                String filedName = field.getName();
                for (int b = 0; b < methods.length; b++) {
                    Method method = methods[b];
                    String methodsName = method.getName();
                    if (methodsName.equalsIgnoreCase("set" + filedName)) {
                        method.invoke(obj, map.get(filedName));
                    }
                }
            }
            // ����и��࣬���õݹ飬��ȡ���������
            if (c.getGenericSuperclass() != null) {
                Class superClass = c.getSuperclass();
                buildObjFromMap(map, superClass, obj);
            }
        } catch (Exception e) {
            log.error("buildObjFromMap occur Exception:" + e.getMessage(), e);
        }

    }
}

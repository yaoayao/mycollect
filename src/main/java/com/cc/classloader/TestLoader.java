package com.cc.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by chengwanchao on 2016/9/27.
 */
public class TestLoader {



    public static void main(String[] args) throws InterruptedException {
//        String jsonStr = "{\"id\":1,\"name\":\"job\"}";
//        User user = JSON.parseObject(jsonStr,User.class);
//        System.out.println("1111");

        String classDataRootPath = "E:\\source\\classloader\\src";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "User";
        String className1 = "User";
        try {
            System.out.println(fscl1.getParent());
            Class<?> class1 = fscl1.findClass(className);
            Object obj1 = class1.newInstance();
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader().getParent();
            System.out.println(classLoader.toString());
            System.out.println("扩展类加载器 的加载路径: ");

            URL[]  urls = classLoader.getURLs();
            for(URL url : urls)
                System.out.println(url);

            System.out.println("----------------------------");


            URL[] urls1  = sun.misc.Launcher.getBootstrapClassPath().getURLs();
            System.out.println("扩展类加载器 的加载路径: ");

            for(URL url : urls1)
                System.out.println(url);

            System.out.println("----------------------------");

            Class<?> class2 = classLoader.loadClass(className1);
//            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setUser", java.lang.Object.class);
//            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

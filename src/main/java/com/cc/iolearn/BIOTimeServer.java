package com.cc.iolearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by wanchao on 2018/1/17.
 * 阻塞io
 */
public class BIOTimeServer {
    public static void main(String[] args) {
        int port = 9999;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server start.......");
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new BIOTimerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

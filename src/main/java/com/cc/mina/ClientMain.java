package com.cc.mina;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by wanchao on 2017/10/26.
 */
public class ClientMain {
    public static void main(String[] args)
    {
        SocketAddress address = new InetSocketAddress("47.95.64.117",3080);
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new DeviceProtocolCodecFactory()));
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.setHandler(new ClientHandler());
        //连接到服务器
        connector.connect(address);
        System.out.println("已经连接到了服务器"+address);
    }

    static class ClientHandler extends IoHandlerAdapter
    {
        @Override
        public void sessionCreated(IoSession session) throws Exception {
            session.write("这是客户端发送的信息");
        }

        //发送信息
        @Override
        public void messageSent(IoSession session, Object message) throws Exception
        {

        }

        //接收信息
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception
        {
            System.out.println("客户端接收到的服务器的信息是"+message);
        }
    }

}

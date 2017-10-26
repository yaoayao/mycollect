package com.cc.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by wanchao on 2017/10/26.
 */
public class ServerMain {
    public static void main(String[] args) throws IOException
    {
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setDefaultLocalAddress(new InetSocketAddress(4321));
        acceptor.setReuseAddress(true);
        SocketSessionConfig config = acceptor.getSessionConfig();
        config.setReuseAddress(true);
        //配置数据的编解码器
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.getFilterChain().addLast("logger",new LoggingFilter());
        acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));

        //绑定服务器端口
        acceptor.setHandler(new ServerHandler());
        acceptor.bind();
        System.out.println("服务器开始在4321端口监听.......");
    }
    static class ServerHandler extends IoHandlerAdapter
    {
        @Override
        public void sessionClosed(IoSession session) throws Exception {
            System.out.println("close-----");
            super.sessionClosed(session);
        }

        //创建会话
        @Override
        public void sessionOpened(IoSession session) throws Exception
        {
            System.out.println("服务器创建了会话 ");
            session.write("服务器创建会话时发送的信息。");
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
            System.out.println("服务端收到信息：" + message);
        }
    }

}

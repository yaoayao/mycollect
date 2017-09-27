package com.cc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wanchao on 2017/9/26.
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {
//        SocketChannel channel = new s
//        Selector selector = Selector.open();
//        channel.configureBlocking(false);
//        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
//        while(true) {
//            int readyChannels = selector.select();
//            if(readyChannels == 0) continue;
//            Set selectedKeys = selector.selectedKeys();
//            Iterator keyIterator = selectedKeys.iterator();
//            while(keyIterator.hasNext()) {
//                SelectionKey key = keyIterator.next();
//                if(key.isAcceptable()) {
//                    // a connection was accepted by a ServerSocketChannel.
//                } else if (key.isConnectable()) {
//                    // a connection was established with a remote server.
//                } else if (key.isReadable()) {
//                    // a channel is ready for reading
//                } else if (key.isWritable()) {
//                    // a channel is ready for writing
//                }
//                keyIterator.remove();
//            }
//        }
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://www.baidu.com", 80));

        while(! socketChannel.finishConnect() ){
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int read = socketChannel.read(buffer);
            while (read != -1) {
                System.out.println("read " + read);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                read = socketChannel.read(buffer);
            }
            //wait, or do something else...
        }
    }
}

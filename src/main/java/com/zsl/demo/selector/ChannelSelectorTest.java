package com.zsl.demo.selector;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelSelectorTest {
    public static void main(String[] args) throws Exception {
        int[] ports = new int[]{
                5000,
                5001,
                5002,
                5003,
                5004,
        };

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress(ports[i]);
            serverSocketChannel.bind(socketAddress);

            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            System.out.println("start listen :" + ports[i]);
        }

        while (true) {
            int count = selector.select();

            System.out.println("count:" + count);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println(selectionKeys.toString());

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("connect:" + socketChannel.getLocalAddress());

                    iterator.remove();
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int readBytesCount = 0;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    byteBuffer.clear();
                    while (true) {
                        int thisReadCount = socketChannel.read(byteBuffer);
                        if (thisReadCount <= 0) {
                            break;
                        }
                        byteBuffer.flip();

                        System.out.println(byteBuffer.array().toString());
                        readBytesCount += thisReadCount;
                    }
                    iterator.remove();


                }



            }

        }

    }
}

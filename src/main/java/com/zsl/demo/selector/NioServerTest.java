package com.zsl.demo.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServerTest {
    public static void main(String[] args) throws IOException {


        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);


        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(9000));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                    try {
                        SocketChannel socketChannel1 = socketChannel.accept();
                        socketChannel1.configureBlocking(false);
                        socketChannel1.register(selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer=ByteBuffer.allocate(512);

                    try {
                        int count=socketChannel.read(byteBuffer);
                        if (count>0)
                        {
                            byteBuffer.flip();
                            Charset charset=Charset.forName("UTF-8");
                            String str=new String(charset.decode(byteBuffer).array());
                            System.out.println(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            selectionKeys.clear();
        }
    }
}

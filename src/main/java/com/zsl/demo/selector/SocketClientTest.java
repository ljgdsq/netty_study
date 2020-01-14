package com.zsl.demo.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClientTest {
    public static void main(String[] args) throws IOException {

        Socket socket=new Socket();

        socket.connect(new InetSocketAddress("localhost",9000));


        socket.getOutputStream().write("hello".getBytes());

        socket.close();

    }
}

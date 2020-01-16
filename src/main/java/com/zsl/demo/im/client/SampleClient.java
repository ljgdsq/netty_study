package com.zsl.demo.im.client;

import com.zsl.demo.im.Handler.*;
import com.zsl.demo.im.protocol.request.LoginRequest;
import com.zsl.demo.im.protocol.request.MessageRequest;
import com.zsl.demo.im.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class SampleClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
//                                .addLast(new ChannelLifeCycleTest())
                                .addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new PacketEncoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new ClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9000).sync();
            channelFuture.addListener(future -> {
                handleConnection(channelFuture);
            });
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            System.out.println("bootstrap error!");
            e.printStackTrace();
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleConnection(ChannelFuture channelFuture) {
        new Thread(() -> {
//
//            MessageRequest messageRequest = new MessageRequest();
//            while (scanner.hasNext()) {
//                String str = scanner.next();
//                messageRequest.setMessage(str);
//                ByteBuf data = PacketCodec.encode(messageRequest);
//                channelFuture.channel().writeAndFlush(data);
//
//            }
            LoginRequest loginRequest = new LoginRequest();
            Scanner scanner = new Scanner(System.in);
            Channel channel = channelFuture.channel();
            boolean isLogin=false;
            while (true) {
                if (!isLogin) {
                    System.out.println("请输入用户名:");
                    String userName = scanner.nextLine();
                    System.out.println("请输入密码:");
                    String pwd = scanner.nextLine();
                    loginRequest.setUserId(channel.hashCode());
                    loginRequest.setUserName(userName);
                    loginRequest.setPwd(pwd);
                    channel.writeAndFlush(loginRequest);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isLogin=true;
                } else {
                    MessageRequest messageRequest = new MessageRequest();
                    System.out.println("发送给ID:");
                    messageRequest.setToUserId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("输入消息:");
                    messageRequest.setMessage(scanner.nextLine());
                    channel.writeAndFlush(messageRequest);
                }

            }

        }).start();
    }
}

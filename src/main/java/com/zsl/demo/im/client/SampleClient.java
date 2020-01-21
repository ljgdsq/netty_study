package com.zsl.demo.im.client;

import com.zsl.demo.im.Handler.*;
import com.zsl.demo.im.console.ConsoleCommandManager;
import com.zsl.demo.im.console.LoginConsoleCommand;
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
                                .addLast(new HeartBeatTimerSender())
                                .addLast(new MessageResponseHandler())
                                .addLast(new CreateGroupResponseHandler())
                                .addLast(new JoinGroupResponseHandler())
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
            ConsoleCommandManager consoleCommandManager=new ConsoleCommandManager();
            LoginConsoleCommand loginConsoleCommand=new LoginConsoleCommand();
            Scanner scanner = new Scanner(System.in);
            Channel channel = channelFuture.channel();
            boolean isLogin=false;
            while (true) {
                if (!isLogin) {
                    loginConsoleCommand.exec(scanner,channel);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isLogin=true;
                } else {
                    consoleCommandManager.exec(scanner,channel);
                }

            }

        }).start();
    }
}

package com.zsl.demo.im.util;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BootUtil {
    private BootUtil() {
    }

    public static void startServer(int port, ChannelHandler childHandler) throws InterruptedException {
        EventLoopGroup parent = new NioEventLoopGroup();
        EventLoopGroup child = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parent, child).channel(NioServerSocketChannel.class)
                .childHandler(childHandler);
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw e;
        } finally {
            parent.shutdownGracefully().sync();
            child.shutdownGracefully().sync();
        }


    }

    public static void startClient(String host, int port, ChannelHandler handler) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(handler);
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            throw e;
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}

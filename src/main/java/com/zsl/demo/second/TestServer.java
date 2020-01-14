package com.zsl.demo.second;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;

public class TestServer {
    public static void main(String[] args) {

        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                .childHandler(new MyServerInitializer());

        try {
            ChannelFuture sync = bootstrap.bind(9000).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

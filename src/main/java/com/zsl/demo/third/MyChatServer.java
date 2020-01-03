package com.zsl.demo.third;

import com.zsl.demo.second.MyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyChatServer {
    public static void main(String[] args) {
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                .childHandler(new MyChatServerInitializer());

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

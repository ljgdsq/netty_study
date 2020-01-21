package com.zsl.demo.im.server;

import com.zsl.demo.im.Handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SampleServer {
    public static void main(String[] args) {
        EventLoopGroup parent = new NioEventLoopGroup();
        EventLoopGroup child = new NioEventLoopGroup();

        int port = 9000;
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parent, child).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new IMIdleStateHandler())
                                .addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new PacketEncoder())
                                .addLast(new HeartBeatRequestHandler())
                                .addLast(new LoginRequestHandler())
                                .addLast(new AuthHandler())
                                .addLast(new MessageRequestHandler())
                                .addLast(new CreateGroupRequestHandler())
                                .addLast(new JoinGroupRequestHandler())
                                .addLast(new ServerHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println("bootstrap error!");
            e.printStackTrace();
        } finally {
            try {
                parent.shutdownGracefully().sync();
                child.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

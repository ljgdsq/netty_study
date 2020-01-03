package com.zsl.demo.third;

import com.zsl.demo.second.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyChatClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();

        try {
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 9000).sync();

            Channel channel = channelFuture.channel();

            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line=br.readLine())!=null){
                channel.writeAndFlush(line+"\r\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
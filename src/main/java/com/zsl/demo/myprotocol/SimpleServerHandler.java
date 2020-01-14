package com.zsl.demo.myprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class SimpleServerHandler extends SimpleChannelInboundHandler<SimpleProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SimpleProtocol msg) throws Exception {
        System.out.println("SimpleServerHandler channelRead0:"+new String(msg.getData(),CharsetUtil.UTF_8));
    }
}

package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.request.HeartBeatPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatPacket msg) throws Exception {

    }

}

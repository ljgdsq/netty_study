package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.response.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) throws Exception {
        System.out.println("recv msg from:"+msg.getFromUserName()+"\n"+msg.getMessage());
    }
}

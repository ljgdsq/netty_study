package com.zsl.demo.myprotocol;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


@ChannelHandler.Sharable
public class SimpleClientHandler extends SimpleChannelInboundHandler<SimpleProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SimpleProtocol msg) throws Exception {

        System.out.println("client recv:" + new String(msg.getData(), CharsetUtil.UTF_8));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 10; i++) {
            SimpleProtocol simpleProtocol = new SimpleProtocol();
            String msg = "呵呵我是客户端!";
            byte[] data = msg.getBytes(CharsetUtil.UTF_8);
            simpleProtocol.setData(data);
            simpleProtocol.setLength(data.length);
            ctx.write(simpleProtocol);
        }
        ctx.flush();
    }
}

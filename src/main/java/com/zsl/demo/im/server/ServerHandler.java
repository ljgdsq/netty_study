package com.zsl.demo.im.server;

import com.zsl.demo.im.Session;
import com.zsl.demo.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf reqBuf = (ByteBuf) msg;
//        Packet packet = PacketCodec.decode(reqBuf);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        Session session = SessionUtil.getSession(ctx.channel());
        System.out.println("["+session.getUserName()+"]离开!");
    }
}

package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.request.HeartBeatPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatTimerSender extends ChannelInboundHandlerAdapter {
    public static final int HEARTBEAT_INTERVAL=5;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx){
        ctx.executor().schedule(()->{
            if (ctx.channel().isActive()){
                ctx.writeAndFlush(new HeartBeatPacket());
                scheduleSendHeartBeat(ctx);
            }
        },HEARTBEAT_INTERVAL,TimeUnit.SECONDS);
    }
}

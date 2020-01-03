package com.zsl.demo.fourth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);

        if (evt instanceof IdleStateEvent){
            IdleStateEvent event= (IdleStateEvent) evt;

//            switch (event.state()){
//                case ALL_IDLE:
//                    System.out.println(ctx.channel().remoteAddress()+" ALL_IDLE");
//                    break;
//            }
            System.out.println(ctx.channel().remoteAddress()+" "+event.state().toString());
            ctx.close();
        }
    }
}

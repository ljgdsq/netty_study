package com.zsl.demo.im.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class IMIdleStateHandler extends IdleStateHandler {
    public static final int READ_IDLE_TIME=15;

    public IMIdleStateHandler(){
        super(READ_IDLE_TIME,0,0,TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READ_IDLE_TIME+"秒内为读取到数据,关闭连接!");
        ctx.channel().close();
    }
}

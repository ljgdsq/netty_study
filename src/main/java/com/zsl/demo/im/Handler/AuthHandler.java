package com.zsl.demo.im.Handler;

import com.zsl.demo.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        boolean login = LoginUtil.isLogin(ctx.channel());
        if (login)
        {
            ctx.pipeline().remove(this);
            ctx.fireChannelRead(msg);
        }else {
            ctx.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        boolean login = LoginUtil.isLogin(ctx.channel());
//        if (login)
//        {
//            System.out.println("用户登录验证通过 AuthHandler handlerRemoved!");
//        }else {
//            System.out.println("用户登录验证失败! 连接关闭!");
//        }
    }

}

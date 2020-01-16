package com.zsl.demo.im.Handler;

import com.zsl.demo.im.Session;
import com.zsl.demo.im.protocol.request.LoginRequest;
import com.zsl.demo.im.protocol.response.LoginResponsePacket;
import com.zsl.demo.im.util.LoginUtil;
import com.zsl.demo.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest loginRequest) throws Exception {

//        System.out.println("recv logging:");
//        System.out.println(loginRequest);

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (validate(loginRequest)) {
            System.out.println("["+loginRequest.getUserName()+"]登录成功!");
            loginResponsePacket.setSuccess(true);
            LoginUtil.setLogging(ctx.channel(), true);
            SessionUtil.bindSession(new Session(loginRequest.getUserId(), loginRequest.getUserName()),ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            System.out.println("["+loginRequest.getUserName()+"]登录失败!");
            LoginUtil.setLogging(ctx.channel(), false);
        }
        loginResponsePacket.setUserId(loginRequest.getUserId());
        ctx.channel().writeAndFlush(loginResponsePacket);

    }

    //TODO validate
    protected boolean validate(LoginRequest requestPacket) {
        if (requestPacket.getPwd().equals("123"))
            return true;
        return false;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}

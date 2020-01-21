package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.StateCode;
import com.zsl.demo.im.protocol.request.JoinGroupResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponse msg) throws Exception {
        if (msg.getStateCode()==StateCode.OK)
        {
            System.out.println("加入群[id="+msg.getGroupId()+"]成功!");
        }else {
            System.out.println("加入群[id="+msg.getGroupId()+"]失败! 原因:"+msg.getReason());
        }
    }
}

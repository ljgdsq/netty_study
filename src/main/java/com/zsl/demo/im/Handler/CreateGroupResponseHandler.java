package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.response.CreateGroupResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponse msg) throws Exception {
        System.out.println("群创建成功 群ID:"+msg.getGroupId()+" 成员("+msg.getUserNameList()+")");
    }
}

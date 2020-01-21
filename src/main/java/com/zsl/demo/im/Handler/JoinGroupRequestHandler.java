package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.StateCode;
import com.zsl.demo.im.protocol.request.JoinGroupRequest;
import com.zsl.demo.im.protocol.request.JoinGroupResponse;
import com.zsl.demo.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequest msg) throws Exception {
        int groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        JoinGroupResponse response=new JoinGroupResponse();
        if (channelGroup!=null)
        {
            response.setStateCode(StateCode.OK);
            response.setGroupId(groupId);
        }else {
            response.setStateCode(StateCode.ERROR);
            response.setReason("该群不存在!");
        }

        ctx.channel().writeAndFlush(response);
    }
}

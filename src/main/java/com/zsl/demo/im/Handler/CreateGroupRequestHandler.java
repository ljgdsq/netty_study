package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.StateCode;
import com.zsl.demo.im.protocol.request.CreateGroupRequest;
import com.zsl.demo.im.protocol.response.CreateGroupResponse;
import com.zsl.demo.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequest msg) throws Exception {
        List<Integer> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for (int userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }

        }
        int groupId = channelGroup.hashCode();
        SessionUtil.bindChannelGroup(groupId,channelGroup);
        CreateGroupResponse response=new CreateGroupResponse();
        response.setGroupId(groupId);
        response.setStateCode(StateCode.OK);
        response.setUserNameList(userNameList);

        channelGroup.writeAndFlush(response);

        System.out.println("群创建成功 群ID:"+groupId+" 成员("+userNameList+")");


    }
}

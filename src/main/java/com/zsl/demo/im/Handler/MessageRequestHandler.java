package com.zsl.demo.im.Handler;

import com.zsl.demo.im.Session;
import com.zsl.demo.im.protocol.request.MessageRequest;
import com.zsl.demo.im.protocol.response.MessageResponse;
import com.zsl.demo.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest messageRequest) throws Exception {
        Session session=SessionUtil.getSession(ctx.channel());
//            String message = messageRequest.getMessage();
//            System.out.println("recv:"+message);
//
//            messageResponse.setMessage("我是服务器 我收到了你的消息 "+message);
//
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setFromUserId(session.getId());
        messageResponse.setFromUserName(session.getUserName());
        messageResponse.setMessage(messageRequest.getMessage());

        int toUserId = messageRequest.getToUserId();
        Channel toUserChannel = SessionUtil.getChannel(toUserId);

        if (toUserChannel!=null && SessionUtil.hasLogin(toUserChannel))
        {
            toUserChannel.writeAndFlush(messageResponse);
        }else {
            System.err.println("[" + messageRequest.getToUserId() + "] 不在线，发送失败!");
        }
    }
}

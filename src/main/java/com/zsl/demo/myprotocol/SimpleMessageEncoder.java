package com.zsl.demo.myprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SimpleMessageEncoder extends MessageToByteEncoder<SimpleProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, SimpleProtocol msg, ByteBuf out) throws Exception {
        System.out.println("SimpleMessageEncoder encode");

        out.writeInt(msg.getLength());
        out.writeBytes(msg.getData());

    }
}

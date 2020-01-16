package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.Packet;
import com.zsl.demo.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.encode(msg,out);

    }
}

package com.zsl.demo.myprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class SimpleMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int length=in.readInt();
        byte[]data=new byte[length];
        in.readBytes(data,0,length);

        SimpleProtocol simpleProtocol=new SimpleProtocol();
        simpleProtocol.setLength(length);
        simpleProtocol.setData(data);
        out.add(simpleProtocol);
        System.out.println("SimpleMessageDecoder decode");
    }
}

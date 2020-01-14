package com.zsl.demo.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyReplayMessageDecoder extends ReplayingDecoder<MyReplayMessageDecoder.MyState> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    }

    public enum MyState {
        READ_LENGTH,
        READ_CONTENT;
    }
}

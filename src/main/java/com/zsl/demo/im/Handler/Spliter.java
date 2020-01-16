package com.zsl.demo.im.Handler;

import com.zsl.demo.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int OFFSET=7;
    private static final int LENGTH =4;


    public Spliter() {
        super(Integer.MAX_VALUE, OFFSET, LENGTH);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex())!=PacketCodec.MAGIC_NUMBER) //deny other protocol
        {
            System.out.println("unknown protocol close connection!");
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
//        System.out.println("recv connection:"+ctx.channel().remoteAddress());
    }
}

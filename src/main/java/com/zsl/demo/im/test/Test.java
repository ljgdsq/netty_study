package com.zsl.demo.im.test;

import com.zsl.demo.im.protocol.request.LoginRequest;
import com.zsl.demo.im.protocol.Packet;
import com.zsl.demo.im.protocol.PacketCodec;
import com.zsl.demo.im.serializer.Serializer;
import com.zsl.demo.im.serializer.impl.JsonSerializer;
import com.zsl.demo.im.util.TraceUtil;
import io.netty.buffer.ByteBuf;

public class Test {
    public static void main(String[] args) {

        TraceUtil.printMethodName();
        LoginRequest loginRequest =new LoginRequest();
        loginRequest.setUserId(3);
        loginRequest.setPwd("123");
        loginRequest.setUserName("tom");

        Packet packet= loginRequest;
        Serializer<LoginRequest> serializer=JsonSerializer.INSTANCE;
        byte[] serialize = serializer.serialize(loginRequest);


        LoginRequest deserialize = serializer.deserialize(LoginRequest.class, serialize);
        System.out.println(deserialize);

        byte[] datas = JsonSerializer.INSTANCE.serialize(packet);
        LoginRequest deserialize1 = serializer.deserialize(LoginRequest.class, datas);

        System.out.println(deserialize1);


        ByteBuf encode = PacketCodec.encode(packet);
        Packet decode = PacketCodec.decode(encode);


    }
}

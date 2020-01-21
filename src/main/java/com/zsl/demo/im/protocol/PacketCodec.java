package com.zsl.demo.im.protocol;

import com.zsl.demo.im.protocol.request.*;
import com.zsl.demo.im.protocol.response.CreateGroupResponse;
import com.zsl.demo.im.protocol.response.LoginResponsePacket;
import com.zsl.demo.im.protocol.response.MessageResponse;
import com.zsl.demo.im.serializer.Serializer;
import com.zsl.demo.im.serializer.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodec {
    public static final int MAGIC_NUMBER = 0x1314;


    private static Serializer serializer = JsonSerializer.INSTANCE;

    public static ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] data = serializer.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.serializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
        return byteBuf;
    }

    public static ByteBuf encode(Packet packet, ByteBuf byteBuf) {
        byte[] data = serializer.serialize(packet);
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.serializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf) {
        //TODO validate magic number
        byteBuf.skipBytes(4);
        //TODO check version
        byteBuf.skipBytes(1);
        byte algorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();

        byte[] byteData = new byte[length];
        byteBuf.readBytes(byteData);

        if (command == Command.HEART_BEAT) {
            return (Packet) serializer.deserialize(HeartBeatPacket.class, byteData);
        } else if (command == Command.LOGIN_REQUEST) {
            return (Packet) serializer.deserialize(LoginRequest.class, byteData);
        } else if (command == Command.LOGIN_RESPONSE) {
            return (Packet) serializer.deserialize(LoginResponsePacket.class, byteData);
        } else if (command == Command.MESSAGE_REQUEST) {
            return (Packet) serializer.deserialize(MessageRequest.class, byteData);
        } else if (command == Command.MESSAGE_RESPONSE) {
            return (Packet) serializer.deserialize(MessageResponse.class, byteData);
        } else if (command == Command.CREATE_GROUP_REQUEST) {
            return (Packet) serializer.deserialize(CreateGroupRequest.class, byteData);
        } else if (command == Command.CREATE_GROUP_RESPONSE) {
            return (Packet) serializer.deserialize(CreateGroupResponse.class, byteData);
        } else if (command == Command.JOIN_GROUP_REQUEST) {
            return (Packet) serializer.deserialize(JoinGroupRequest.class, byteData);
        } else if (command == Command.JOIN_GROUP_RESPONSE) {
            return (Packet) serializer.deserialize(JoinGroupResponse.class, byteData);
        }
        return null;
    }

    public static void setSerializer(Serializer serializer) {
        PacketCodec.serializer = serializer;
    }
}

package com.zsl.demo.im.protocol.request;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

public class HeartBeatPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT;
    }
}

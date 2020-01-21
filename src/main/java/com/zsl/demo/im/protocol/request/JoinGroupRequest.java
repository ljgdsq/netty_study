package com.zsl.demo.im.protocol.request;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

public class JoinGroupRequest extends Packet {
    private int groupId;
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}

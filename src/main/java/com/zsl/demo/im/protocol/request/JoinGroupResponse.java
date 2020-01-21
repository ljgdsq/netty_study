package com.zsl.demo.im.protocol.request;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

public class JoinGroupResponse extends Packet {

    private int stateCode;
    private int groupId;

    private String reason;
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

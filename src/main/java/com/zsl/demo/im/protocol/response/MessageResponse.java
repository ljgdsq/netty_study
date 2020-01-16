package com.zsl.demo.im.protocol.response;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

public class MessageResponse extends Packet {
    private String message;
    private String fromUserName;
    private int fromUserId;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}

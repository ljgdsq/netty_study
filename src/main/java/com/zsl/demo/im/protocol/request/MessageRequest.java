package com.zsl.demo.im.protocol.request;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

public class MessageRequest extends Packet {

    private String message;
    private int toUserId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}

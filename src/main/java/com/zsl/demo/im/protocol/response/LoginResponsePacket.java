package com.zsl.demo.im.protocol.response;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

public class LoginResponsePacket extends Packet {

    private Integer userId;
    private boolean success;
    private String reason;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }


}

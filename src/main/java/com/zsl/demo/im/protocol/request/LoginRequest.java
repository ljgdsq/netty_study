package com.zsl.demo.im.protocol.request;

import com.zsl.demo.im.protocol.Packet;

import static com.zsl.demo.im.protocol.Command.LOGIN_REQUEST;

public class LoginRequest extends Packet {
    private Integer userId;
    private String userName;
    private String pwd;


    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    @Override
    public String toString() {
        return "LoginRequest{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

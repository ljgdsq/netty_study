package com.zsl.demo.im.protocol;

public interface Command {
    Byte HEART_BEAT=0;
    Byte LOGIN_REQUEST=1;
    Byte LOGIN_RESPONSE=2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;
    Byte JOIN_GROUP_REQUEST = 7;
    Byte JOIN_GROUP_RESPONSE = 8;
}

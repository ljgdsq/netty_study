package com.zsl.demo.im.protocol.request;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

import java.util.List;

public class CreateGroupRequest extends Packet {
   private List<Integer> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userList) {
        this.userIdList = userList;
    }
}

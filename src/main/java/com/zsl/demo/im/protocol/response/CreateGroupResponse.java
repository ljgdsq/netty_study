package com.zsl.demo.im.protocol.response;

import com.zsl.demo.im.protocol.Command;
import com.zsl.demo.im.protocol.Packet;

import java.util.List;

public class CreateGroupResponse extends Packet {

    private List<String> userNameList;
    private int groupId;
    private int stateCode;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }


    public List<String> getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(List<String> userNameList) {
        this.userNameList = userNameList;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }
}

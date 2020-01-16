package com.zsl.demo.im.protocol.request;

import java.util.List;

public class CreateGroup {
    private List<Integer> userList;

    public List<Integer> getUserList() {
        return userList;
    }

    public void setUserList(List<Integer> userList) {
        this.userList = userList;
    }
}

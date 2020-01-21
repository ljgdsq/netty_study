package com.zsl.demo.im.console;

import com.zsl.demo.im.protocol.request.CreateGroupRequest;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {
   public static final String SPLITER=",";

    @Override
    public void exec(Scanner scanner, Channel channel) {

        CreateGroupRequest packet=new CreateGroupRequest();

        System.out.println("[拉人群聊]输入user id列表，user id之间以英文逗号隔开:");
        String userId=scanner.nextLine();
        String[] split = userId.split(SPLITER);
        List<Integer> ids=new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            ids.add(Integer.parseInt(split[i]));
        }
        packet.setUserIdList(ids);
        channel.writeAndFlush(packet);
    }
}

package com.zsl.demo.im.console;

import com.zsl.demo.im.protocol.request.JoinGroupRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequest request=new JoinGroupRequest();

        System.out.println("输入群id,加入群聊:");
        String groupId = scanner.nextLine();
        request.setGroupId(Integer.parseInt(groupId));
        channel.writeAndFlush(request);
    }
}

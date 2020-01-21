package com.zsl.demo.im.console;

import com.zsl.demo.im.protocol.request.MessageRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        MessageRequest messageRequest = new MessageRequest();
        System.out.println("发送给ID:");
        messageRequest.setToUserId(Integer.parseInt(scanner.nextLine()));
        System.out.println("输入消息:");
        messageRequest.setMessage(scanner.nextLine());
        channel.writeAndFlush(messageRequest);
    }
}

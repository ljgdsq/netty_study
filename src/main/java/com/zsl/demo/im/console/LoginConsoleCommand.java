package com.zsl.demo.im.console;

import com.zsl.demo.im.protocol.request.LoginRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequest loginRequest = new LoginRequest();
        System.out.println("请输入用户名:");
        String userName = scanner.nextLine();
        System.out.println("请输入密码:");
        String pwd = scanner.nextLine();
        loginRequest.setUserId(channel.hashCode());
        loginRequest.setUserName(userName);
        loginRequest.setPwd(pwd);
        channel.writeAndFlush(loginRequest);
    }
}

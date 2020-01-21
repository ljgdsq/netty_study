package com.zsl.demo.im.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
//        consoleCommandMap.put("sendToUser",)
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.nextLine();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(scanner,channel);
        }else {
            System.out.println("无法识别["+command+"]指令");
        }
    }
}

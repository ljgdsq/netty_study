package com.zsl.demo.im.util;

import com.zsl.demo.im.attribute.Attributes;
import io.netty.channel.Channel;

public class LoginUtil {
    private LoginUtil(){}

    public static void setLogging(Channel channel,boolean isLogging)
    {
        channel.attr(Attributes.LOGIN).set(isLogging);
    }

    public static boolean isLogin(Channel channel){
        if(channel.attr(Attributes.LOGIN).get())
            return true;
        return false;
    }
}

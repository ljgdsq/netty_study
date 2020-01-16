package com.zsl.demo.im.util;

import com.zsl.demo.im.Session;
import com.zsl.demo.im.attribute.Attributes;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    private SessionUtil() {
    }

    private static final Map<Integer, Channel> userChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userChannelMap.put(session.getId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        Session session = getSession(channel);
        if (session != null) {
            userChannelMap.remove(getSession(channel).getId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }


    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static boolean hasLogin(Channel channel) {

        return channel.hasAttr(Attributes.SESSION);
    }

    public static Channel getChannel(int userId) {

        return userChannelMap.get(userId);
    }

}

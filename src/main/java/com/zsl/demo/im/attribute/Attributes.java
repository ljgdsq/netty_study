package com.zsl.demo.im.attribute;

import com.zsl.demo.im.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
   AttributeKey<Boolean> LOGIN=AttributeKey.newInstance("login");
   AttributeKey<Session> SESSION=AttributeKey.newInstance("session");
}

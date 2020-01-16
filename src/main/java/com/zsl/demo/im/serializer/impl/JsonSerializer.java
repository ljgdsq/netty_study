package com.zsl.demo.im.serializer.impl;

import com.google.gson.Gson;
import com.zsl.demo.im.serializer.Serializer;
import com.zsl.demo.im.serializer.SerializerAlgorithm;
import io.netty.util.CharsetUtil;

public class JsonSerializer<T> implements Serializer<T> {
    private JsonSerializer() {
    }

    public static JsonSerializer INSTANCE=new JsonSerializer();
    private static Gson gson=new Gson();
    @Override
    public byte serializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return gson.toJson(object).getBytes(CharsetUtil.UTF_8);
    }

    @Override
    public T deserialize(Class<T> clazz, byte[] data) {
        return gson.fromJson(new String(data,CharsetUtil.UTF_8),clazz);
    }
}

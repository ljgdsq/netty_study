package com.zsl.demo.im.serializer;

public interface Serializer<T> {
    byte serializerAlgorithm();
    byte[] serialize(Object object);

    T deserialize(Class<T> clazz,byte[]data);

}

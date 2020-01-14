package com.zsl.demo.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TestStudentProto {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Student.StudentInfo.Builder builder = Student.StudentInfo.newBuilder();
        Student.StudentInfo tom = builder.setId(1).setName("tom").build();
        byte[] data = tom.toByteArray();


        Student.StudentInfo studentInfo = Student.StudentInfo.parseFrom(data);

        System.out.println(studentInfo);
        System.out.println(studentInfo.getMarried());

        IntBuffer intBuffer=IntBuffer.allocate(10);


    }
}

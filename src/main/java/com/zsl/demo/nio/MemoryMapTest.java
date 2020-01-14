package com.zsl.demo.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class MemoryMapTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile ras=new RandomAccessFile("a.txt","rw");
        MappedByteBuffer mappedByteBuffer = ras.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, ras.length());
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(7, (byte) 'W');
        ras.close();

    }
}

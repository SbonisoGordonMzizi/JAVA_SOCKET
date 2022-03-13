package com.tech;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.IOException;


public class MyFileChannel {
    public static void main(String[] args) throws IOException{
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        RandomAccessFile file1 = new RandomAccessFile("file.txt","r");
        RandomAccessFile file2 = new RandomAccessFile("last.txt","rw");
        FileChannel fileChannel = file1.getChannel();
        FileChannel fileChannel1 = file2.getChannel();
        fileChannel.read(byteBuffer);
        fileChannel.force(true);
        for(int index = 0;index<fileChannel.position();index++) {
            System.out.print((char) byteBuffer.get(index));
        }
        byteBuffer.flip();
        fileChannel1.write(byteBuffer);
        fileChannel1.force(true);


    }
}

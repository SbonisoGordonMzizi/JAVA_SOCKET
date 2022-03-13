package com.tech;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.GatheringByteChannel;

public class ScatterAndGather {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(4);
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(4);
        ByteBuffer buffer3 = ByteBuffer.allocateDirect(5);
        ByteBuffer buffer4 = ByteBuffer.allocateDirect(5);

        ByteBuffer buffer5 = ByteBuffer.wrap("i am leaning\n".getBytes());
        ByteBuffer buffer6 = ByteBuffer.wrap("gordon\n".getBytes());
        ByteBuffer buffer7 = ByteBuffer.wrap("i love Java\n".getBytes());
        ByteBuffer buffer8 = ByteBuffer.wrap("java and scala\n".getBytes());

        ByteBuffer[] buffers = {buffer1,buffer2,buffer3,buffer4};
        ByteBuffer[] buffers1 = {buffer5,buffer6,buffer7,buffer8};
        FileInputStream file = new FileInputStream("file.txt");
        ScatteringByteChannel src = (ScatteringByteChannel) Channels.newChannel(file);

        src.read(buffers);
        for(ByteBuffer buffer: buffers){
            buffer.flip();
            while(buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
        }


        FileOutputStream fileOutputStream = new FileOutputStream("file2.txt");
        GatheringByteChannel dst = (GatheringByteChannel) Channels.newChannel(fileOutputStream);
        dst.write(buffers1);




    }
}

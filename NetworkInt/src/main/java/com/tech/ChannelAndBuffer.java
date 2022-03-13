package com.tech;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;


public class ChannelAndBuffer {
    public static void main(String[] args) {

        ReadableByteChannel sourceChannel = Channels.newChannel(System.in);
        WritableByteChannel destChannel = Channels.newChannel(System.out);

        try{
            copy(sourceChannel,destChannel);
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }


    }
    private static void copy(ReadableByteChannel source,WritableByteChannel dest) throws IOException{
        ByteBuffer dataBuffer = ByteBuffer.allocateDirect(2048);
        while (source.read(dataBuffer) != 1){
            dataBuffer.flip();
            dest.write(dataBuffer);
            dataBuffer.compact();
        }
        dataBuffer.flip();
        while(dataBuffer.hasRemaining()){
            dest.write(dataBuffer);
        }
        dataBuffer.clear();
    }
}

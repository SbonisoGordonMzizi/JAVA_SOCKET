package com.tech;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.io.IOException;

public class TimeClient {
    public static void main(String[] args){
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",5000);
        try(SocketChannel socketChannel = SocketChannel.open(addr)){
            int bytesRead = socketChannel.read(byteBuffer);
            while(bytesRead > 0){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.print((char)byteBuffer.get());
                }
                System.out.println();
                bytesRead = socketChannel.read(byteBuffer);
            }
        }catch (IOException io){
            System.out.println(io.fillInStackTrace());
        }
    }
}

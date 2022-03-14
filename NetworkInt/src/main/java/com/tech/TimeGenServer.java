package com.tech;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Date;


public class TimeGenServer {
    public static void main(String[] args) throws IOException{
        System.out.println("Staring Server ");
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(5000));
        ByteBuffer buffer = ByteBuffer.allocate(64);
        System.out.println("Wating for requst...");
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null){
                String dateAndTime ="Date: "+new Date(System.currentTimeMillis());
                buffer.put(dateAndTime.getBytes());
                buffer.flip();
                while (buffer.hasRemaining()){
                    socketChannel.write(buffer);
                }
                System.out.println("Sent: "+dateAndTime);
                buffer.clear();
            }
        }
    }
}

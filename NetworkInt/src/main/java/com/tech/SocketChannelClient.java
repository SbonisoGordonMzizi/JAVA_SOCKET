package com.tech;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.net.ServerSocket;

public class SocketChannelClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",6000);
        socketChannel.connect(addr);

        while (!socketChannel.finishConnect()){
            System.out.println("waiting to finish connection");
        }
        if(socketChannel.isConnected()){
            System.out.println("Connected To Server");
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(2048);

        int h = socketChannel.read(buffer);
        System.out.println(h);
        h = socketChannel.read(buffer);
        System.out.println(h);
        h = socketChannel.read(buffer);
        System.out.println(h);
        socketChannel.close();
    }
}

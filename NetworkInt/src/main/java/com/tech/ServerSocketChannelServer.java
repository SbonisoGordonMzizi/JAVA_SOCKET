package  com.tech;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class ServerSocketChannelServer {
    public static void main(String[] args) throws IOException {
        String message = "I AM MR SERVER HELLO THERE!!!!!!";

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket socketChannel = serverSocketChannel.socket();
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",6000);
        socketChannel.bind(addr);
        String serverAddress = socketChannel.getLocalSocketAddress().toString();
        System.out.print("STARTING SERVER "+serverAddress+" ");
        byte[] data = (message+"  "+serverAddress).getBytes();
        ByteBuffer dataBuffer = ByteBuffer.wrap(data);

        while (true){
            System.out.print(".");
            SocketChannel sc = serverSocketChannel.accept();
            if(sc != null){
                System.out.println();
                System.out.println("Recived connection from "+sc.socket().getRemoteSocketAddress());
                dataBuffer.flip();
                while (dataBuffer.hasRemaining()) {
                    sc.write(dataBuffer);
                }
                System.out.println("Done talking");
                dataBuffer.rewind();
                sc.close();

            }else{
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ie){
                    System.out.println(ie.getMessage());
                }

            }

        }
    }

}
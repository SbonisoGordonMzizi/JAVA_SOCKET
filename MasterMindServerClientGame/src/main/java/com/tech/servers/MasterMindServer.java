package com.tech.servers;

import com.tech.logic.MasterMindLogic;
import com.tech.utils.ServerClientConst;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MasterMindServer {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static InputStream inStream;
    private static OutputStream outStream;
    private static BufferedReader inStreamData;
    private static InputStreamReader streamReader;
    private static MasterMindLogic masterMindLogic = new MasterMindLogic();


    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(ServerClientConst.SERVER_PORT.getPortNumber());
            while(true) {
                System.out.println("[SERVER] waiting for client connection");
                clientSocket = serverSocket.accept();
                System.out.println("Client " + clientSocket.getInetAddress().toString() + " Connected");

                inStream = clientSocket.getInputStream();
                outStream = clientSocket.getOutputStream();

                streamReader = new InputStreamReader(inStream);
                inStreamData = new BufferedReader(streamReader, 8192);
                PrintWriter printWriter = new PrintWriter(outStream, true);
                String data = "";
                boolean win = false;
                masterMindLogic.startGame();
                System.out.println(masterMindLogic.getCode());
                while (masterMindLogic.hasLife() || data.equals("off") || win==false) {
                    data = inStreamData.readLine();
                    if(masterMindLogic.checkCode(data)){
                        win = true;
                        data = "WINNER!!!";
                    }

                    if (data != null) {
                        //System.out.println(data);
                        printWriter.println("CLIENT " + data);
                    } else if(data.equals("done")) {
                        break;
                    }
                    masterMindLogic.takeLife();

                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                inStream.close();
                outStream.close();
                serverSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}

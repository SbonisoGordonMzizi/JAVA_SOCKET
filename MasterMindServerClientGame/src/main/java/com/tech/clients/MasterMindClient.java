package com.tech.clients;

import java.io.*;
import java.net.Socket;
import com.tech.utils.ServerClientConst;
import java.util.Scanner;

public class MasterMindClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static Socket clientSocket;
    private static InputStream inputStream;
    private static OutputStream outputStream;
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;
    private static Scanner readInput = new Scanner(System.in);

    private static  String getUserInput(){
        System.out.print("> ");
        return readInput.nextLine();
    }

    public static void main(String[] args) {
        try {
            clientSocket = new Socket(SERVER_IP, ServerClientConst.SERVER_PORT.getPortNumber());
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            printWriter = new PrintWriter(outputStream,true);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data = "";
            while(true) {
                data = getUserInput();
                if(data.equals("off")){
                    break;
                }
                printWriter.println(data);
                System.out.println(bufferedReader.readLine());
            }


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                outputStream.close();
                clientSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

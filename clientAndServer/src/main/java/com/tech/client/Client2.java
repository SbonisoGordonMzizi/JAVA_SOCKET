package com.tech.client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) throws IOException,InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);
        InputStream inputStream = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String clientName = Thread.currentThread().getName();
        PrintWriter printWriter = new PrintWriter(out, true);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader input = new BufferedReader(inputStreamReader, 8192);
        int count = 0;
        while (true) {

            printWriter.println("I AM GORDON SIR "+clientName);

            String serverResponse = input.readLine();
            JOptionPane.showMessageDialog(null, serverResponse);
            if(count == 10) {
                input.close();
                System.exit(0);
            }
            count++;
            Thread.sleep(10);
        }
    }
}

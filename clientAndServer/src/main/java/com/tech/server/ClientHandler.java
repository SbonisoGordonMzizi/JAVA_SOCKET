package com.tech.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
    }


    @Override
    public void run() {

            try {
                while(true) {
                    String string = in.readLine();
                    if(string != null){
                        System.out.println(string);
                        if (string.equalsIgnoreCase("name")) {
                            out.println(Server.name[(int) (Math.random() * Server.name.length)]);
                        } else {
                            out.println(Server.getRandomName());
                        }
                    }
                }
            } catch (IOException e) {

            } finally {
                try {
                    in.close();
                } catch (IOException e) {

                }
                out.close();

            }

    }
}

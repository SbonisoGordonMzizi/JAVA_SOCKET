package com.tech.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private final static int PORT = 8080;
    public static String[] name = {"Gordon","Vice","Blony","Tom","Jesu"};
    private static String[] adjs = {"The gentle","The un-gentle","The overwronght","the urban"};

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket listing = new ServerSocket(PORT);
        while (true) {
            System.out.println("[SERVER] waiting for connection");
            Socket socket = listing.accept();
            System.out.println("[SERVER] connected to client");
            ClientHandler clientHandler = new ClientHandler(socket);
            clients.add(clientHandler);
            pool.execute(clientHandler);
        }

    }

    public static String getRandomName(){
        String n = name[(int)(Math.random() * name.length)];
        String nn = adjs[(int)(Math.random()*adjs.length)];
        return n +" "+nn;
    }
}

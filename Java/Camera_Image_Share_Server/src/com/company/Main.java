package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        Lock lock = new Lock();
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            while(true){
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread =
                        new ClientThread(clientSocket, lock);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

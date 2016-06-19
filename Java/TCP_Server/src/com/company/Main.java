package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static boolean go = true;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            while (go) {
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

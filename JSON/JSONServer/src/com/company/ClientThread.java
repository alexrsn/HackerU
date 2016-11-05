package com.company;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by resin on 05/11/2016.
 */

public class ClientThread extends Thread {
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String messageFromClient;
    private boolean inputStreamClosed = false, outputStreamClosed = false;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024];
            int actuallyRead = inputStream.read(buffer);
            if(actuallyRead != -1){
                messageFromClient = new String(buffer, 0, actuallyRead);
            }
            try {
                JSONObject jsonObjectFromClient = new JSONObject(messageFromClient);
                System.out.println(jsonObjectFromClient.get("firstName"));
                System.out.println(jsonObjectFromClient.get("lastName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject jsonToClient = new JSONObject();
            try {
                jsonToClient.put("firstName", "Moshe");
                jsonToClient.put("lastName", "Cohen");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            outputStream.write(jsonToClient.toString().getBytes());
            outputStream.flush();

            inputStream.close();
            inputStreamClosed = true;
            outputStream.close();
            outputStreamClosed = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!inputStreamClosed && inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (!outputStreamClosed && outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}

package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by resin on 01/06/2016.
 */
public class MessageServlet extends javax.servlet.http.HttpServlet {
    private String message = "";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        InputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[1024];
        int actuallyRead;
        StringBuilder stringBuilder = new StringBuilder();
        while ((actuallyRead = inputStream.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, actuallyRead));
        }
        inputStream.close();
        String messageFromClient = stringBuilder.toString();
        if (messageFromClient == null) {
            return;
        }
        OutputStream outputStream = response.getOutputStream();
        String[] keyValue = messageFromClient.split("=");
        String key = keyValue[0];
        if (keyValue.length == 1) {
            if (key.equals("requestMessage")) {
                outputStream.write(message.getBytes());
            }
        } else if (keyValue.length == 2) {
            String value = keyValue[1];
            if (key.equals("message")) {
                message = value;
                outputStream.write("message saved".getBytes());
            }
        }
        outputStream.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String queryString = request.getQueryString();
        //....?action=send&message=hello
        if(queryString == null)
            return;
        String[] keyValuePairs = queryString.split("&");
        if(keyValuePairs.length > 0){
            String[] keyValue = keyValuePairs[0].split("=");
            if(keyValue.length != 2)
                return;
            if(!keyValue[0].equals("action"))
                return;
            String action = keyValue[1];
            switch (action){
                case "send":
                    keyValue = keyValuePairs[1].split("=");
                    if(keyValue.length != 2)
                        return;
                    if(!keyValue[0].equals("message"))
                        return;
                    message = keyValue[1];
                    response.getWriter().write("result=ok");
                    break;
                case "get":
                    response.getWriter().write("message="+message);
                    break;
            }
        }
    }
}
package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by resin on 29/05/2016.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("in doPost");
        InputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[1024];
        int actuallyRead;
        StringBuilder stringBuilder = new StringBuilder();
        while ((actuallyRead=inputStream.read(buffer))!=-1){
            stringBuilder.append(new String(buffer, 0, actuallyRead));
        }
        inputStream.close();
        String messageFromClient = stringBuilder.toString();
        System.out.println(messageFromClient);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write("thanks client".getBytes());
        outputStream.close();

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //System.out.println("in doGet request: " + request.getQueryString());
        String querySting = request.getQueryString();
        if(querySting != null) {
            String[] keyValuePairs = querySting.split("&");
            String key1 = null, key2 = null;
            if (keyValuePairs != null) {
                for (String keyValuePair : keyValuePairs) {
                    String[] keyAndValue = keyValuePair.split("=");
                    if (keyAndValue != null && keyAndValue.length == 2) {
                        String key = keyAndValue[0];
                        String value = keyAndValue[1];
                        if (key.equals("key1"))
                            key1 = value;
                        else if (key.equals("key2"))
                            key2 = value;
                    }
                }
            }
            if (key1 != null)
                System.out.println("key1=" + key1);
            if (key2 != null)
                System.out.println("key2=" + key2);
            response.getWriter().write("<h1>Thank you!</h1>");
        }
    }
}

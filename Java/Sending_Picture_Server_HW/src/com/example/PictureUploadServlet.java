package com.example;

import java.io.*;

/**
 * Created by resin on 23/06/2016.
 */
public class PictureUploadServlet extends javax.servlet.http.HttpServlet {
    File photoFile = new File("C:\\test\\photo.jpg");

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        InputStream inputStream = request.getInputStream();
        OutputStream outputStream = new FileOutputStream(photoFile);
        byte[] buffer = new byte[1024];
        int actuallyRead;
        while ((actuallyRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, actuallyRead);
        }
        inputStream.close();
        outputStream.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}

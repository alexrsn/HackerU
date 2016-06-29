package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by resin on 25/06/2016.
 */
@WebServlet(name = "PictureDownloadServlet")
public class PictureDownloadServlet extends HttpServlet {
    File photoFile = new File("C:\\test\\photo.jpg");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = new FileInputStream(photoFile);
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int actuallyRead;
        while ((actuallyRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, actuallyRead);
        }
        inputStream.close();
        outputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

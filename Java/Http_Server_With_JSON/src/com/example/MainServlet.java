package com.example;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Created by resin on 08/06/2016.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {

    HashMap<String,String> users;

    @Override
    public void init() throws ServletException {//works like constructor on start of the server
        super.init();

        users = new HashMap<>();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        JSONObject jsonUser;
        String messageFromClient = null;
        InputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[1024];
        int actuallyRead = inputStream.read(buffer);
        inputStream.close();
        if(actuallyRead != -1){
            messageFromClient = new String(buffer, 0, actuallyRead);
        }
        try {
            jsonUser = new JSONObject(messageFromClient);
            String firstName = jsonUser.getString("firstName");
            String lastName = jsonUser.getString("lastName");
            System.out.println(firstName + " " + lastName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("result","ok");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(jsonObject.toString().getBytes());
        outputStream.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}

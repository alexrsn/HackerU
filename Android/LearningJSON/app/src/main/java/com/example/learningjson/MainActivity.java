package com.example.learningjson;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {

        String messageToSend = null;
        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("firstName", "John");
            jsonUser.put("lastName", "Doe");
            messageToSend = jsonUser.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncTask<String,Void,String>(){

            @Override
            protected String doInBackground(String... params) {
                String responseFromServer = null;
                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;
                OutputStream outputStream = null;
                boolean inClosed = false, outClosed = false;
                try {
                    URL url = new URL("http://10.0.2.2:8080/MainServlet");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setUseCaches(false);
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestMethod("POST");
                    outputStream = urlConnection.getOutputStream();
                    outputStream.write(params[0].getBytes());
                    outputStream.close();
                    outClosed = true;
                    inputStream = urlConnection.getInputStream();
                    byte[] buffer = new byte[1024];
                    int actuallyRead = inputStream.read(buffer);
                    inputStream.close();
                    inClosed = true;
                    if(actuallyRead != -1){
                        responseFromServer = new String(buffer,0 , actuallyRead);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(!inClosed && inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!outClosed && outputStream != null){
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(urlConnection!=null)
                        urlConnection.disconnect();
                }
                return responseFromServer;
            }

            @Override
            protected void onPostExecute(String responseFromServer) {
                try {
                    JSONObject jsonObject = new JSONObject(responseFromServer);
                    String result = jsonObject.getString("result");
                    Toast.makeText(MainActivity.this, "result:" + result, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(messageToSend);
    }
}

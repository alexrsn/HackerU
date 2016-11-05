package com.example.alex.jsonclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    TextView txtResponseFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResponseFromServer = (TextView) findViewById(R.id.txtResponseFromServer);

    }

    public void sendJsonClicked(View view) {
        new AsyncTask<Integer, Void, String>() {
            @Override
            protected String doInBackground(Integer... params) {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                String responseFromServer = null;
                try {
                    Socket clientSocket = new Socket("10.0.2.2", 3000);
                    inputStream = clientSocket.getInputStream();
                    outputStream = clientSocket.getOutputStream();
//                    byte hexToServer = 0x30;
//                    outputStream.write(hexToServer);
                    JSONObject jsonToServer = new JSONObject();
                    try {
                        jsonToServer.put("firstName", "John");
                        jsonToServer.put("lastName", "Smith");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    outputStream.write(jsonToServer.toString().getBytes());
                    outputStream.flush();

                    byte[] buffer = new byte[1024];
                    int actuallyRead = inputStream.read(buffer);
                    if(actuallyRead != -1){
                        responseFromServer = new String(buffer, 0, actuallyRead);
                    }

                    outputStream.close();
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    if (inputStream != null)
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if (outputStream != null)
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

                return responseFromServer;
            }

            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    JSONObject jsonFromServer = new JSONObject(str);
                    stringBuilder.append("First Name:");
                    stringBuilder.append(jsonFromServer.get("firstName") + "\n");
                    stringBuilder.append("Last Name:");
                    stringBuilder.append(jsonFromServer.get("lastName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                txtResponseFromServer.setText(stringBuilder.toString());
            }
        }.execute();
    }
}

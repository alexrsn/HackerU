package com.example.messagehwhttpclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private EditText txtMessageToSend;
    private TextView txtCurrentMessage;
    private Button btnSendMessage;
    private boolean exitFlag = false;
    private Handler handler;
    private String currentMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();

        txtMessageToSend = (EditText) findViewById(R.id.txtMessageToSend);
        txtCurrentMessage = (TextView) findViewById(R.id.txtCurrentMessage);
        btnSendMessage = (Button) findViewById(R.id.btnSendMessage);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!exitFlag) {
                    currentMessage = httpPost("requestMessage");
                    handler.post(new MessageRunnable(currentMessage, txtCurrentMessage));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void btnSendMessage(View view) {
        final String messageToSend = txtMessageToSend.getText().toString();
        if(messageToSend.isEmpty()){
            Toast.makeText(MainActivity.this, "Please type a message", Toast.LENGTH_SHORT).show();
            return;
        }
        btnSendMessage.setEnabled(false);
        txtMessageToSend.setEnabled(false);
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return httpPost("message=" + params[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                btnSendMessage.setEnabled(true);
                txtMessageToSend.setEnabled(true);
                if(s.equals("message saved"))
                    txtMessageToSend.setText("");
            }
        }.execute(messageToSend);
    }

    public void btnExit(View view) {
        exitFlag = true;
        finish();
    }

    private String httpPost(String queryString) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        String response = null;
        boolean inputStreamClosed = false, outputStreamClosed = false, urlConnectionClosed = false;

        try {
            url = new URL("http://10.0.2.2:8080/MessageServlet");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "text/plain");
            urlConnection.connect();
            outputStream = urlConnection.getOutputStream();
            outputStream.write(queryString.getBytes());
            outputStream.close();
            outputStreamClosed = true;
            inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int actuallyRead;
            StringBuilder stringBuilder = new StringBuilder();
            while ((actuallyRead = inputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, actuallyRead));
            }
            inputStream.close();
            inputStreamClosed = true;
            urlConnection.disconnect();
            urlConnectionClosed = true;
            response = stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!outputStreamClosed && outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!inputStreamClosed && inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!urlConnectionClosed && urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return response;
    }
}

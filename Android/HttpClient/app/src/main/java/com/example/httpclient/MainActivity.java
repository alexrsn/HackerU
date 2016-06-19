package com.example.httpclient;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView label;
    //Handler handler = new Handler();
    int counter = 0;
    Button bthSendHttpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = (TextView) findViewById(R.id.label);
        bthSendHttpRequest = (Button) findViewById(R.id.bthSendHttpRequest);

    }

    public void btnSendHttpRequest(View view) {

        bthSendHttpRequest.setEnabled(false);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                //return httpPost();
                return httpGet();
            }

            @Override
            protected void onPostExecute(String s) {
                bthSendHttpRequest.setEnabled(true);
            }

        }.execute();
    }


    private String httpPost(){
        URL url = null;
        HttpURLConnection urlConnection = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        String response= null;
        boolean inputStreamClosed = false, outputStreamClosed = false, urlConnectionClosed = false;

        try {
            url = new URL("http://10.0.2.2:8080/MainServlet");//10.0.2.2 local ip of the emulator
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "text/plain");
            urlConnection.connect();
            outputStream = urlConnection.getOutputStream();
            outputStream.write("hi server!, how are you?".getBytes());
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
        } finally {//finally will run even if exception occurs
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

    private String httpGet() {
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String response = null;
        boolean inputStreamClosed = false, urlConnectionClosed = false;

        try {
            url = new URL("http://10.0.2.2:8080/MainServlet?key1=value1&key2=value2");//10.0.2.2 local ip of the emulator
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "text/plain");
            urlConnection.connect();
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
        } finally {//finally will run even if exception occurs
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

        /*label.setText("counter=" + counter);
        counter++;
        AsyncTask<Integer, Void, Integer> asyncTask = new  AsyncTask<Integer, Void, Integer>(){

            @Override
            protected Integer doInBackground(Integer... params) {
                int x = 0;
                for (int i = 0; i < params[0]; i++) {
                    for (int j = 0; j < 500; j++) {
                        x += 1;
                    }
                }
                return x;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                label.setText("the value of x is: " + integer);//will be executed after doInBackground finish in main thread
            }
        };
        asyncTask.execute(50000000);*/



        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int x = 0;
                for (int i = 0; i < 50000000; i++) {
                    for (int j = 0; j < 500; j++) {
                        x += 1;
                    }
                }
                Log.d("Alex", "the value of x is: " + x);
                handler.post(new MyRunnable(x,label));
            }
        });
        thread.start();
    }

    public static class MyRunnable implements Runnable{
        int x;
        TextView textView;

        public MyRunnable(int x, TextView textView) {
            this.x = x;
            this.textView = textView;
        }

        @Override
        public void run() {
            textView.setText("the value of x is: " + x);
        }
    }*/
}

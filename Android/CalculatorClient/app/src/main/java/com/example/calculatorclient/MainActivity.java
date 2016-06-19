package com.example.calculatorclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView answer;
    private EditText firstNumberEditText, secondNumberEditText;
    private boolean processing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = (TextView) findViewById(R.id.answer);
        firstNumberEditText = (EditText) findViewById(R.id.firstNumber);
        secondNumberEditText = (EditText) findViewById(R.id.secondNumber);

    }

    public void calculate(final View view) {
        if (processing)
            return;

        new AsyncTask<Character, Integer, String>() {
            @Override
            protected String doInBackground(Character... params) {
                char operator = params[0];
                processing = true;
                httpGet(operator);
                publishProgress(30);
                return httpPost(operator);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {//we can update ui here depend on progress
                int progress = values[0];
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                processing = false;
                answer.setText(s);
            }
        }.execute(view.getTag().toString().charAt(0));
    }

    private String httpPost(char operator) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        String response = null;
        boolean inputStreamClosed = false, outputStreamClosed = false, urlConnectionClosed = false;

        try {
            url = new URL("http://10.0.2.2:8080/Servlet");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "text/plain");
            urlConnection.connect();
            outputStream = urlConnection.getOutputStream();
            outputStream.write(("firstNumber=" + firstNumberEditText.getText().toString()
                    + "&secondNumber=" + secondNumberEditText.getText().toString()
                    + "&operator=" + operator).getBytes());
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

    private String httpGet(char operator) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String response = null;
        boolean inputStreamClosed = false, urlConnectionClosed = false;

        try {
            url = new URL("http://10.0.2.2:8080/Servlet?firstNumber=" + firstNumberEditText.getText()
                    + "&secondNumber=" + secondNumberEditText.getText() + "&operator=" + operator);
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
        } finally {
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

package com.example.alex.homismarttest;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by david on 11/6/2016.
 */

public class GetJSONThread extends Thread {

    private Handler handler;
    private URL url;
    private TextView textView;

    public GetJSONThread(Handler handler, URL url, TextView textView) {
        this.handler = handler;
        this.url = url;
        this.textView = textView;
    }

    @Override
    public void run() {
        try {
            JSON jsonData = saveToJsonDataFromUrl(url);
            setTextFromJson(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private JSON saveToJsonDataFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        JSONObject jsonObject;
        JSON jsonData = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            int actuallyRead;
            byte[] buffer = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while ((actuallyRead = inputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, actuallyRead));
            }
            try {
                jsonObject = new JSONObject(stringBuilder.toString());
                String host = jsonObject.get("Host").toString();
                String userAgent = jsonObject.get("User-Agent").toString();
                jsonData = new JSON(host, userAgent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } finally {
            if (inputStream != null)
                inputStream.close();
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return jsonData;
    }

    private void setTextFromJson(final JSON jsonData) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                StringBuilder jsonDataString = new StringBuilder();
                jsonDataString.append("Host:" + jsonData.getHost() + "\n");
                jsonDataString.append("User-Agent:" + jsonData.getUserAgent());
                textView.setText(jsonDataString);
            }
        });
    }
}


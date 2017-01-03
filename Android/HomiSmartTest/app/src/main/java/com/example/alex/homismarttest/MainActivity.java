package com.example.alex.homismarttest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView txtJSON;
    ImageView img;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtJSON = (TextView) findViewById(R.id.txtJSON);
        img = (ImageView) findViewById(R.id.img);
        handler = new Handler();

    }

    public void getJsonFromURL(View view) {
        try {
            URL url = new URL("http://headers.jsontest.com/");
            GetJSONThread jsonThread = new GetJSONThread(handler, url, txtJSON);
            jsonThread.start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getImageFromURL(View view) {
        try {
            URL url = new URL("https://static.wixstatic.com/media/66ad1e_7fe1b057b4d24348ac9cb8d0cbb2a4ab.png");
            GetImageThread getImageThread = new GetImageThread(this, handler, url, img);
            getImageThread.start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}

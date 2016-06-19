package com.example.callingfromyourapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String phoneNumber = "+972501234567";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+" + phoneNumber));
        startActivity(intent);
    }
}

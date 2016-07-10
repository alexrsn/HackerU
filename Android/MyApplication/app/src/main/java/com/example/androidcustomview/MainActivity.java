package com.example.androidcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyButton myButton = (MyButton) findViewById(R.id.myButton1);
        myButton.setText("Click Me");
        myButton.setTextSize(120);
    }
}

package com.example.relativelayout;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.stamView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer.valueOf((String)v.getTag());
            }
        });
    }
}

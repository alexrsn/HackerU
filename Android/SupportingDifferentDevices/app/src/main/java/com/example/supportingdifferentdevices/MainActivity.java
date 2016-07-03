package com.example.supportingdifferentdevices;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblHi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblHi = (TextView) findViewById(R.id.lblHi);

        if (lblHi == null) { //check orientation if not exist will be null
            Log.d("Alex", "Portrait mode");
        }

        int orientation = getResources().getConfiguration().orientation;
        Log.d("Alex", "orientation: "
                + (orientation == Configuration.ORIENTATION_PORTRAIT ? "portrait" : "landscape"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

        }

    }
}

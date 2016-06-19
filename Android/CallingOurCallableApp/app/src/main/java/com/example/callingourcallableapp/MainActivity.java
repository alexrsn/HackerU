package com.example.callingourcallableapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final int REQUEST_CODE = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(Intent.ACTION_VIEW);
        Intent intent = new Intent("calculateSum");
        intent.putExtra("x", 3);
        intent.putExtra("y", 7);
        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int result = data.getIntExtra("result", 0);
            Toast.makeText(MainActivity.this, "result = " + result, Toast.LENGTH_SHORT).show();
        }
    }
}
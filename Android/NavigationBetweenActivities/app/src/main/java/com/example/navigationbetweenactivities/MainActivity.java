package com.example.navigationbetweenactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnGoToSecondActivity(View view) {
        go(false);
    }

    public void btnGoToThirdActivity(View view) {
        go(true);
    }

    private void go(boolean shouldGoToThird) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("shouldGoToThird", shouldGoToThird);
        startActivity(intent);
    }
}

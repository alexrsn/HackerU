package com.example.navigationbetweenactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    void go(boolean shouldGoToMain){
        Intent intent = new Intent();
        intent.putExtra("shouldGoToMain", shouldGoToMain);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void btnGoToMainActivity(View view) {
        go(true);
    }

    public void btnGoToSecondActivity(View view) {
        go(false);
    }
}

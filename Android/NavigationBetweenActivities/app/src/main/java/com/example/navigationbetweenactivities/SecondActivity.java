package com.example.navigationbetweenactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

    public static final int REQUEST_CODE = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        boolean shouldGoToThird = getIntent().getBooleanExtra("shouldGoToThird", false);
        if(shouldGoToThird)
            goToThird();
    }

    void goToThird(){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void btnGoToMainActivity(View view) {
        finish();
    }

    public void btnGoToThirdActivity(View view) {
        goToThird();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            boolean shouldGoToMain = data.getBooleanExtra("shouldGoToMain", false);
            if(shouldGoToMain)
                finish();
        }
    }





}

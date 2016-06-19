package com.example.sendingsmsmessages1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String phoneNumber = "+972501234567";
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse( "sms:" + phoneNumber));
        //intent.putExtra("sms:", "0501234567");
        intent.putExtra("sms_body", "Hello friend!");
        //intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);
    }
}

package com.resin.learningbroadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    //MyBroadcastReceiver myBroadcastReceiver;
    //IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myBroadcastReceiver = new MyBroadcastReceiver();
        //intentFilter = new IntentFilter("MY_SPECIFIC_ACTION");
        //intentFilter.setPriority(10);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //registerReceiver(myBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(myBroadcastReceiver);
    }

    public void btnSendBroadcast(View view) {
        Intent intent = new Intent("MY_SPECIFIC_ACTION");
        intent.putExtra("key", "some value...");
        //sendBroadcast(intent);
        sendOrderedBroadcast(intent, null);
    }


}

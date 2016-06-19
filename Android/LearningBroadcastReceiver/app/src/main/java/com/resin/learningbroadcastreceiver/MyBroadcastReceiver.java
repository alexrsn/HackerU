package com.resin.learningbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Received broadcast, value received: " +
                intent.getStringExtra("key"), Toast.LENGTH_SHORT).show();
    }
}
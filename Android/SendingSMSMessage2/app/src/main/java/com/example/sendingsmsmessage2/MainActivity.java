package com.example.sendingsmsmessage2;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String SENT = "SMS_SENT";
    static final String DELIVERED = "SMS_DELIVERED";
    PendingIntent sendPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        //SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage("0501234567",null, "Hello Friend",null,null);

        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String result = "";
                switch (getResultCode()) {
                    case RESULT_OK:
                        result = "SMS sent";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        result = "generic failure";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        result = "no service";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        result = "Null PDU";
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        result = "radio off";
                        break;
                }
                Toast.makeText(MainActivity.this, "result:" + result, Toast.LENGTH_LONG).show();
            }
        };

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String result = "";
                switch (getResultCode()) {
                    case RESULT_OK:
                        result = "SMS delivered";
                        break;
                    case RESULT_CANCELED:
                        result = "not delivered";
                        break;
                }
                Toast.makeText(MainActivity.this, "result:" + result, Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(smsSentReceiver,new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver,new IntentFilter(DELIVERED));
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("0501234567",null, "Hello Friend",sendPI,deliveredPI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }
}

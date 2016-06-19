package com.example.readphonestate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MainActivity extends AppCompatActivity {


    TelephonyManager telephonyManager;
    PhoneReceiver phoneReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //phoneReceiver = new PhoneReceiver(this);
        //telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //telephonyManager.listen(phoneReceiver, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //telephonyManager.listen(phoneReceiver, PhoneStateListener.LISTEN_NONE);
    }
}

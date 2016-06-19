package com.example.readphonestate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneStateReceiver extends BroadcastReceiver {

    TelephonyManager telephonyManager;
    PhoneReceiver phoneReceiver;
    static boolean alreadyListening = false;


    @Override
    public void onReceive(Context context, Intent intent) {
        phoneReceiver = new PhoneReceiver(context);
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (!alreadyListening) {
            telephonyManager.listen(phoneReceiver, PhoneStateListener.LISTEN_CALL_STATE);
            alreadyListening = true;
        }


    }
}

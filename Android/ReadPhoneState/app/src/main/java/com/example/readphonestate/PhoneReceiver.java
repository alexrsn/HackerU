package com.example.readphonestate;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneReceiver extends PhoneStateListener {

    private Context context;

    public PhoneReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        String stateName = null;

        switch (state){
            case TelephonyManager.CALL_STATE_IDLE:
                stateName = "Idle";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                stateName = "Ringing";
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:;
                stateName = "Offhook";
                break;
            default:
                stateName = "Other";
                break;
        }

        Toast.makeText(context, "onCallStateChanged state=" + stateName
                + " incomingNumber=" + incomingNumber, Toast.LENGTH_SHORT).show();

    }
}

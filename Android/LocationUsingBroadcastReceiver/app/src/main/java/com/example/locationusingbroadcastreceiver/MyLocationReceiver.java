package com.example.locationusingbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

public class MyLocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String provider = "";
        if (intent.getBooleanExtra(LocationManager.KEY_PROVIDER_ENABLED, false)) {
            provider = "enabled";
        } else {
            provider = "disabled";
        }
        Toast.makeText(context, "provider " + provider, Toast.LENGTH_SHORT).show();
        if (intent.hasExtra(LocationManager.KEY_LOCATION_CHANGED)) {
            Location location = (Location) intent.getExtras().get(LocationManager.KEY_LOCATION_CHANGED);
            Toast.makeText(context, "location changed: lat=" + location.getLatitude()
                    + ", long=" + location.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }
}

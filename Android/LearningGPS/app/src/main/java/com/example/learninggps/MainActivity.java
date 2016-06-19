package com.example.learninggps;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    Location hackerU = new Location("GPS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        hackerU.setLatitude(32.084709);
        hackerU.setLongitude(34.800762);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    private class MyLocationListener implements LocationListener {


        //onLocationChanged-Called when the location has changed
        @Override
        public void onLocationChanged(Location location) {
            if(location.distanceTo(hackerU)<50){
                Toast.makeText(getBaseContext(), "welcome to hackeru", Toast.LENGTH_SHORT).show();
            }

            if (location != null) {
                Toast.makeText(getBaseContext(), "location changed lat:" + location.getLatitude()
                        + "long:" + location.getLongitude(), Toast.LENGTH_SHORT).show();
            }
        }

        //onStatusChanged - Called when the status of the
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            String s = "";
            switch (status) {
                case LocationProvider.AVAILABLE:
                    s = "available";
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    s = "out of service";
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    s = "temporarily unavailable";
                    break;
            }
            Toast.makeText(getBaseContext(), provider + " " + s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {

        }


        //onProviderDisabled - Called when the user has disabled the location provider
        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}

package com.example.learningservice;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText txtAddToCounter;
    private MyService myService;
    boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtAddToCounter = (EditText) findViewById(R.id.txtAddToCounter);
    }

    public void btnStartService(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void btnStopService(View view) {
        if (bound) {
            bound = false;
            myService.stopService();
            unbindService(connection);

        }
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "disconnected", Toast.LENGTH_SHORT).show();
            bound = false;
        }
    };


    public void btnNadav(View view) {
        if (bound)
            myService.nadav(100);
    }


    public void btnShowNotification(View view) {
        myService.showNotification();
    }
}
package com.example.servicehw;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyService myService;
    private boolean bound = false;
    private Button btnStartStopService;
    private Button btnStartStopThread;
    private TextView lblThreadCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartStopService = (Button) findViewById(R.id.btnStartStopService);
        btnStartStopThread = (Button) findViewById(R.id.btnStartStopThread);
        lblThreadCounter = (TextView) findViewById(R.id.lblThreadCounter);

        startService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public void startService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
        startService(intent);

    }

    public void btnStartStopService(View view) {
        if (myService == null)
            return;
        if (bound) {
            bound = false;
            myService.stopService();
            unbindService(connection);
            btnStartStopService.setText("Start Service");
            btnStartStopThread.setEnabled(false);
            lblThreadCounter.setText("Counter:");
        } else {
            startService();
            btnStartStopService.setText("Stop Service");
            btnStartStopThread.setEnabled(true);
            btnStartStopThread.setText("start thread");
        }
    }

    public void btnStartStopThread(View view) {
        if (myService == null)
            return;
        if (myService.isThreadRunning())
            btnStartStopThread.setText("start thread");
        else
            btnStartStopThread.setText("stop thread");
        myService.startStopThread();

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            bound = true;
            if (!myService.isThreadRunning())
                btnStartStopThread.setText("start thread");
            else
                btnStartStopThread.setText("stop thread");
            myService.setTextView(lblThreadCounter);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "disconnected", Toast.LENGTH_SHORT).show();
            bound = false;
        }
    };


}

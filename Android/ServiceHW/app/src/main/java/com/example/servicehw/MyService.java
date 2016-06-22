package com.example.servicehw;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;



public class MyService extends Service {

    private int counter = 0;
    private Thread thread;
    private boolean threadRunning = false;
    private Handler handler = new Handler();
    private IBinder binder = new MyBinder();
    private TextView textView;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service is starting ", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyService.this, "service is destroyed", Toast.LENGTH_SHORT).show();
    }

    public void stopService() {
        stopThread();
        stopSelf();
    }

    public void startStopThread() {
        if(thread==null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadRunning = true;
                    while (threadRunning) {
                        counter++;
                        handler.post(new CounterRunnable(counter, textView));
                        Log.d("Alex", "counter=" + counter);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }else
            stopThread();
    }

    public void stopThread() {
        if (thread != null) {
            threadRunning = false;
            thread.interrupt();
            thread = null;
        }
    }

    public boolean isThreadRunning() {
        return threadRunning;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
}

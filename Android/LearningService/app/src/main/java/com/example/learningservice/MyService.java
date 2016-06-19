package com.example.learningservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private int counter = 0;
    private Thread thread;
    private boolean go = true;
    private IBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "service created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service is starting ", Toast.LENGTH_SHORT).show();
        startThread();
        return START_STICKY;//START_STICKY - if service/app closed it will restart service every time
    }

    public void nadav(int x){
        Toast.makeText(this, "nadav " + x, Toast.LENGTH_SHORT).show();
    }

    public void stopService(){
        stopThread();
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyService.this, "service is done", Toast.LENGTH_SHORT).show();
        stopThread();
    }

    private void stopThread() {
        if(thread != null) {
            go = false;
            thread.interrupt();
            thread = null;
        }
    }

    private void startThread(){
        if(thread == null){
            go = true;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(go){
                        counter++;
                        Log.d("Elad", "thread is running " + MyService.this.counter);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            });
            thread.start();
        }
    }

    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    public void showNotification() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MyService.this);
                notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
                notificationBuilder.setContentTitle("new notification");
                notificationBuilder.setContentText("hello");
                notificationBuilder.setAutoCancel(true);//will close notification on tap

                Intent intent = new Intent(MyService.this, MainActivity.class);//MainActivity.class - activity that starts on tap
                PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notificationBuilder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationBuilder.setLights(0xFFFF0000, 500, 500);//set light of notification led + on&off intervals
                notificationManager.notify(1, notificationBuilder.build());
            }
        });
        thread.start();
    }
}

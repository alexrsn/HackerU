package com.example.readingoutgoingsmsmessages;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    ContentResolver contentResolver;
    ContentObserver contentObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentResolver = getContentResolver();

        contentObserver = new ContentObserver(new Handler()) {

            @Override
            public void onChange(boolean selfChange) {
                Uri smsUri = Uri.parse("content://sms/sent");
                String[] columns = new String[]{"address","date","body","type"};
                Cursor c = contentResolver.query(smsUri,columns, null, null,null);
                if(c.moveToNext()){
                    String recipient = c.getString(c.getColumnIndex(columns[0]));
                    String date = c.getString(c.getColumnIndex(columns[1]));
                    String message = c.getString(c.getColumnIndex(columns[2]));
                    String type = c.getString(c.getColumnIndex(columns[3]));
                    Log.d("Alex","outgoing message "+ recipient + " " + date + " " + message + " " + type);
                }
                c.close();//must to close connection to database on end of using
            }

            @Override
            public boolean deliverSelfNotifications() {
                return super.deliverSelfNotifications();
            }
        };
        contentResolver.registerContentObserver(Uri.parse("content://sms"), true, contentObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentResolver.unregisterContentObserver(contentObserver);
    }
}

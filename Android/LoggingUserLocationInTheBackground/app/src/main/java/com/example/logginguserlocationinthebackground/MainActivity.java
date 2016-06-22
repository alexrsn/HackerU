package com.example.logginguserlocationinthebackground;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try (DBAdapter dbAdapter = new DBAdapter(this)) {//try with resources,will AutoClose the connection
            dbAdapter.open(true);
            long rowId = -2;
            //rowId = dbAdapter.addLocation(System.currentTimeMillis(), 34.234, 42.564);
            //Log.d("Alex", "row inserted:" + rowId);
            //rowId = dbAdapter.addLocation(System.currentTimeMillis(), 32.434, 40.164);
            //Log.d("Alex", "row inserted:" + rowId);
            try (Cursor cursor = dbAdapter.getLocations()) {//Cursor extends Closable
                while (cursor.moveToNext()) {
                    Location location = new Location(cursor.getLong(0), cursor.getDouble(1), cursor.getDouble(2));
                    Log.d("Alex", "location: " + location);
                }
            }
        }
    }
}

package com.example.logginguserlocationinthebackground;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by resin on 19/06/2016.
 */
public class DBAdapter extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private Context context;

    private static final String DATABASE_NAME = "MyDb.db";
    private static final String TABLE_NAME = "Locations";
    private static final String KEY_DATE = "date";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";
    private static final String CREATE_LOCATIONS =
            "CREATE TABLE " + TABLE_NAME + " (" + KEY_DATE + " INTEGER," + KEY_LAT + " REAL," +
            KEY_LNG + " REAL)";


    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public long addLocation (long time, double latitude,double longitude){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE,time);
        values.put(KEY_LAT,latitude);
        values.put(KEY_LNG,longitude);
        long rowId = db.insert(TABLE_NAME,null,values);//inserts values to database
        db.close();//very important to close db after use
        return rowId;
    }


    public List<Location> getLocations(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_DATE,KEY_LAT,KEY_LNG},null,null,null,null,null);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOCATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

}

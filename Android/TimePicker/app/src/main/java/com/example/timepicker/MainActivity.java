package com.example.timepicker;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends Activity {

    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
    }

    public void btnOnClick(View view) {
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        //Toast.makeText(MainActivity.this, (hour < 10 ? "0" + hour : hour) + ":" +
        //        (minute < 10 ? "0" + minute : minute), Toast.LENGTH_SHORT).show();

        NumberFormat formatter = new DecimalFormat("00");
        String time = formatter.format(hour) + ":" + formatter.format(minute);
        Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();

    }


}

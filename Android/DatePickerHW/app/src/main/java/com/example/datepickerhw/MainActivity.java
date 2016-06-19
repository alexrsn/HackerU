package com.example.datepickerhw;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
    }

    public void btnOnClick(View view) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();

        Toast.makeText(MainActivity.this, day + "/"  + month + "/" + year, Toast.LENGTH_SHORT).show();

    }
}

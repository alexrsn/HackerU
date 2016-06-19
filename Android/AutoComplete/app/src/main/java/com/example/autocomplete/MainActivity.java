package com.example.autocomplete;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private String[] cities = {"Tel Aviv", "Ramat Gan", "Jerusalem", "Haifa", "Kfar Saba",
        "Ra'anana", "Hertselia", "Hod Hasharon", "Ramat HaSharon", "Ashkelon", "Ashdod",
        "Lod", "Be'er Sheva", "Tiberious", "Katsrin", "Naharya", "Dimona", "Petah Tikva"};
    private AutoCompleteTextView txtCities;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        txtCities = (AutoCompleteTextView)findViewById(R.id.txtCities);
        txtCities.setThreshold(2);//autocomplete after 2 chars entered
        txtCities.setAdapter(adapter);


    }

    public void btnSave(View view) {
        String typedCity = txtCities.getText().toString();
        if(adapter.getPosition(typedCity) < 0){
            adapter.add(typedCity);
        }

        Toast.makeText(MainActivity.this, "you typed: " + typedCity, Toast.LENGTH_SHORT).show();
    }
}

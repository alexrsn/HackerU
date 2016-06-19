package com.example.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    /*private String[] cities = {"Tel Aviv", "Ramat Gan", "Ramat HaSharon", "Ra'anana",
            "Kfar Saba", "Hertselia", "Eilat", "Jerusalem", "Hadera", "Haifa", "Rishon Lezion",
            "Tiberius", "Rosh Ha'ain", "Lod", "Ramla", "Bat Yam", "Rosh Hanikra", "Ashkelon",
            "Be'er Sheva", "Katsrin", "Elad", "Shoham", "Petah Tikva"};*/

    private City[] cities = {
            new City("Tel Aviv",R.drawable.pic1,"non-stop city"),
            new City("Ramat Gan",R.drawable.pic2,""),
            new City("Ramat HaSharon",R.drawable.pic3,""),
            new City("Ra'anana",R.drawable.pic1,""),
            new City( "Kfar Saba",R.drawable.pic2,""),
            new City("Hertselia",R.drawable.pic3,""),
            new City("Eilat",R.drawable.pic1,""),
            new City("Jerusalem",R.drawable.pic2,""),
            new City("Hadera",R.drawable.pic3,""),
            new City("Haifa",R.drawable.pic1,""),
            new City("Rishon Lezion",R.drawable.pic2,""),
            new City("Tiberius",R.drawable.pic3,""),
            new City("Rosh Ha'ain",R.drawable.pic1,""),
            new City("Lod",R.drawable.pic2,""),
            new City("Ramla",R.drawable.pic3,""),
            new City("Bat Yam",R.drawable.pic1,""),
            new City("Rosh Hanikra",R.drawable.pic2,""),
            new City("Ashkelon",R.drawable.pic3,""),
            new City("Be'er Sheva",R.drawable.pic1,""),
            new City("Katsrin",R.drawable.pic2,""),
            new City("Elad",R.drawable.pic3,""),
            new City("Shoham",R.drawable.pic1,""),
            new City("Petah Tikva",R.drawable.pic2,"")
    };

    private ListView listView;
    private ArrayAdapter<City> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new CustomArrayAdapter(this,cities);
        listView.setAdapter(arrayAdapter);

    }
}

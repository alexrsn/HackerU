package com.example.learninglistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] cities = {"Tel Aviv", "Ramat Gan", "Ramat HaSharon", "Ra'anana",
            "Kfar Saba", "Hertselia", "Eilat", "Jerusalem", "Hadera", "Haifa", "Rishon Lezion",
            "Tiberius", "Rosh Ha'ain", "Lod", "Ramla", "Bat Yam", "Rosh Hanikra", "Ashkelon",
            "Be'er Sheva", "Katsrin", "Elad", "Shoham", "Petah Tikva"};

    private String[] countries = {"Israel", "Jordan", "Egypt", "Syria", "Lebanon", "Iraq", "Turkey", "Saudi Arabia"};

    private ListView listView1,listView2;

    private ArrayAdapter<String> arrayAdapter1,arrayAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView)findViewById(R.id.listView1);
        listView2 = (ListView)findViewById(R.id.listView2);

        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);
        arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,cities);
        arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);

        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "You have clicked " + cities[position], Toast.LENGTH_SHORT).show();

                CheckedTextView item = (CheckedTextView)view;
                Toast.makeText(MainActivity.this, "You have " + (item.isChecked() ? "checked " : "unchecked ")
                        + cities[position], Toast.LENGTH_SHORT).show();
            }
        });


    }
}

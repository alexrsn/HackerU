package com.example.modifyingdatasourceoflistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list;
    private ArrayAdapter<String> adapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        for (int i = 10000; i < 11000; i++) {
            list.add(String.valueOf(i));
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    public void btnChangeData(View view) {
        list.set(0, list.get(0) + "a");
        adapter.notifyDataSetChanged();//updates views of ListView that appear on screen

    }
}

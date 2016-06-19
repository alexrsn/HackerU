package com.example.checkboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkBox = (CheckBox)findViewById(R.id.chkAutosave);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), ((CheckBox)v).isChecked() ? "checked" : "unchecked",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}

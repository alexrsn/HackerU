package com.example.buttons;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Button 3 was clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        button4.setOnClickListener(btnListener);
        button5.setOnClickListener(btnListener);

    }


    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getBaseContext(),  ((Button)v).getText() +  " was clicked!",
                    Toast.LENGTH_SHORT).show();
        }
    };




    public void btnOnClick(View view) {
        Toast.makeText(getBaseContext(),  ((Button)view).getText() +  " was clicked!",
                Toast.LENGTH_SHORT).show();
    }

    public void btnOnToggle(View view) {
        ToggleButton toggleButton = (ToggleButton)view;
        Toast.makeText(this, "Toggle mode: " + toggleButton.isChecked() ,
                Toast.LENGTH_SHORT).show();
    }
}



















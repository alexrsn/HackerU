package com.example.imagebuttons;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button5 = (Button)findViewById(R.id.button5);
        button1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.myimage,
                R.mipmap.ic_launcher,0,R.drawable.myimage);
        button5.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.mipmap.ic_launcher);
    }

    public void btnOnClick(View view) {
    }
}

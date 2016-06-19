package com.example.implementingpaging;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPageAdapter myPageAdapter = new MyPageAdapter();
        ViewPager viewPager= (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setCurrentItem(1);
    }

    public void onClick(View view){
        int tag = Integer.valueOf((String) view.getTag());
        Toast.makeText(MainActivity.this, "Butoon " + tag + " clicked", Toast.LENGTH_SHORT).show();
    }
}

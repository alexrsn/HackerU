package com.example.starrankinghw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private LinearLayout ratings;
    private CheckBox star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratings = (LinearLayout) findViewById(R.id.ratings);
        for (int i = 1; i <= 5; i++) {
            star = (CheckBox) ratings.findViewWithTag(String.valueOf(i));
            star.setOnClickListener(starsListener);
        }

    }

    private OnClickListener starsListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = Integer.valueOf((String) v.getTag());
            for (int i = 1; i <= tag; i++) {
                star = (CheckBox) ratings.findViewWithTag(String.valueOf(i));
                star.setChecked(true);
            }
            for (int i = tag + 1; i <= 5; i++) {
                star = (CheckBox) ratings.findViewWithTag(String.valueOf(i));
                star.setChecked(false);
            }
        }
    };
}


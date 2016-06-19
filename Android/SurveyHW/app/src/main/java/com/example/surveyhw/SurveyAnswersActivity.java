package com.example.surveyhw;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SurveyAnswersActivity extends Activity {

    private int numOfQuestions;
    private LinearLayout layout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_answers);

        layout = (LinearLayout) findViewById(R.id.layout);
        numOfQuestions = getIntent().getIntExtra("NumberOfQuestions",0);
        for (int i = 1; i <= numOfQuestions; i++) {
            textView = new TextView(this);
            textView.setTextSize(20);
            textView.setText("Question " + i + ": " + getIntent().getIntExtra(String.valueOf(i),0) + " stars");
            layout.addView(textView);
        }
    }
}

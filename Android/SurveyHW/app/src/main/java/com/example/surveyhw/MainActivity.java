package com.example.surveyhw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int NUMOFQUESTIONS = 15;
    private LinearLayout layout;
    private MySurveyView mySurveyView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.layout);
        intent = new Intent(this,SurveyAnswersActivity.class);
        intent.putExtra("NumberOfQuestions",NUMOFQUESTIONS);
        for (int i = 1; i <= NUMOFQUESTIONS; i++) {
            mySurveyView = new MySurveyView(this, i +".Question?",i,  new MyOnClickListener(layout,intent,i));
            layout.addView(mySurveyView);
        }
        Button button = new Button(this);
        button.setText("Submit");
        layout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public static class MySurveyView extends LinearLayout {

        private TextView textView;
        private CheckBox star;

        public MySurveyView(Context context, String text,int questionNumber, OnClickListener onClickListener) {
            super(context);
            this.setOrientation(VERTICAL);
            textView = new TextView(context);
            textView.setText(text);
            this.addView(textView);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            for (int i = 0; i < 5; i++) {
                star = new CheckBox(context,null,android.R.attr.starStyle);
                star.setTag(questionNumber+""+(i+1));
                star.setOnClickListener(onClickListener);
                linearLayout.addView(star);
            }
            this.addView(linearLayout);
        }
    }

    public static class MyOnClickListener implements View.OnClickListener {

        private int questionNumber;
        private CheckBox star;
        private LinearLayout linearLayout;
        private Intent intent;

        public MyOnClickListener(LinearLayout linearLayout,Intent intent, int questionNumber) {
            this.questionNumber = questionNumber;
            this.linearLayout = linearLayout;
            this.intent = intent;
        }

        @Override
        public void onClick(View v) {
            int tag = Integer.valueOf((String) v.getTag());
            intent.putExtra(String.valueOf(questionNumber),tag%(questionNumber*10));
            for (int i = 1; i <= tag%(questionNumber*10); i++) {
                star = (CheckBox) linearLayout.findViewWithTag(String.valueOf(questionNumber)+""+String.valueOf(i));
                star.setChecked(true);
            }
            for (int i = tag%(questionNumber*10) + 1; i <= 5; i++) {
                star = (CheckBox) linearLayout.findViewWithTag(String.valueOf(questionNumber)+""+String.valueOf(i));
                star.setChecked(false);
            }
        }
    }
}
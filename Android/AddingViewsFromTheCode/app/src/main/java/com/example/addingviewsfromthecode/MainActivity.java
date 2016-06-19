package com.example.addingviewsfromthecode;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        TextView textView = new TextView(this);
        textView.setText("you may now go to a break...");
        layout.addView(textView);
        for (int i = 0; i < 5; i++) {
            MyOnClickListener myOnClickListener = new MyOnClickListener(this, i);
            MyView myView = new MyView(this, "Press button " + i, "click me " + i, myOnClickListener);
            layout.addView(myView);
        }
    }

    public static class MyOnClickListener implements View.OnClickListener {

        private int x;
        private Context context;

        public MyOnClickListener(Context context, int x) {
            this.x = x;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "hi " + x, Toast.LENGTH_SHORT).show();
        }
    }

    public static class MyView extends LinearLayout {

        private TextView textView;
        private Button button;

        public MyView(Context context, String text, String buttonText, OnClickListener onClickListener) {
            super(context);
            this.setOrientation(HORIZONTAL);
            textView = new TextView(context);
            textView.setText(text);
            //textView.setBackgroundColor(0xFF02B505);//FF-alpha, 02-red, B5-green, 05-blue
            textView.setBackgroundColor(getResources().getColor(R.color.colorAlex));//color saved in colors.xml
            this.addView(textView);
            button = new Button(context);
            button.setText(buttonText);
            this.addView(button);
            button.setOnClickListener(onClickListener);

        }

        public void setText(String text) {
            textView.setText(text);
        }

        public void setButtonText(String text) {
            button.setText(text);
        }
    }
}

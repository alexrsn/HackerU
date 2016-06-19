package com.example.memorygame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultsActivity extends Activity implements View.OnClickListener {

    public static final int REMATCH = 5;
    public static final int QUIT = 6;
    private TextView textView;
    private Button rematchButton,quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        textView = (TextView) findViewById(R.id.activity_results_score_text_view);
        rematchButton = (Button) findViewById(R.id.activity_results_play_again_button);
        quitButton = (Button) findViewById(R.id.activity_results_quit_button);

        int score = getIntent().getIntExtra("SCORE",0);
        textView.setText("Your score is: " + score);
        textView.setGravity(Gravity.CENTER);
        rematchButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==rematchButton){
            setResult(REMATCH);
        }else{
            setResult(QUIT);
        }
        finish();
    }
}

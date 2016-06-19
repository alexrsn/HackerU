package com.example.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private static final int GAME_OVER_REQUEST_CODE = 1;
    private LinearLayout mainLayout;
    private int counter = 0;
    private int[] images = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,
            R.drawable.five,R.drawable.six};
    private int[] numbers = {0,0,1,1,2,2,3,3,4,4,5,5};
    private int turns = 0,pairsFound = 0;
    private ImageView clickedCard,previousCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.activity_main_layout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        for (int i = 0; i < 4; i++) {
            LinearLayout rowLayout = new LinearLayout(this);
            ImageView imageView;
            for (int j = 0; j < 3; j++) {
                imageView = new ImageView(this);
                imageView.setTag(counter++);
                imageView.setImageResource(R.drawable.card_back);
                imageView.setOnClickListener(myOnClickListener);
                rowLayout.addView(imageView,params);
            }
            mainLayout.addView(rowLayout,params);
            shuffle();
        }
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickedCard = (ImageView) v;
            clickedCard.setImageResource(images[numbers[(int) v.getTag()]]);
            if(clickedCard != previousCard) {
                if (turns % 2 != 0) {
                    if (numbers[(int) previousCard.getTag()] == numbers[(int) v.getTag()]) {
                        clickedCard.setOnClickListener(null);
                        previousCard.setOnClickListener(null);
                        pairsFound++;
                    } else {
                        FlipThread flipThread = new FlipThread(clickedCard, previousCard);
                        flipThread.start();
                    }
                } else {
                    previousCard = clickedCard;
                }
                turns++;
                if (pairsFound == images.length) {
                    Intent intent = new Intent(MainActivity.this,ResultsActivity.class);
                    intent.putExtra("SCORE",turns);
                    startActivityForResult(intent, GAME_OVER_REQUEST_CODE);
                }
            }else {
                Toast.makeText(MainActivity.this, "Same card!!!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GAME_OVER_REQUEST_CODE){
            if(resultCode == ResultsActivity.REMATCH){
                startNewGame();
            }else if (resultCode == ResultsActivity.QUIT){
                finish();
            }
        }
    }

    private void startNewGame() {
        counter = 0;
        pairsFound = 0;
        turns = 0;
        ImageView imageView;
        for (int i = 0; i < numbers.length; i++) {
            imageView = (ImageView) mainLayout.findViewWithTag(i);
            imageView.setImageResource(R.drawable.card_back);
            imageView.setOnClickListener(myOnClickListener);
        }

        shuffle();
    }

    private void shuffle() {
        Random random = new Random(System.currentTimeMillis());
        int r,temp;
        for (int i = 0; i < numbers.length; i++) {
            r = random.nextInt(numbers.length);
            temp=numbers[i];
            numbers[i]=numbers[r];
            numbers[r]=temp;
        }
    }
}

package com.example.memorygame;

import android.os.Handler;
import android.widget.ImageView;

/**
 * Created by resin on 08/05/2016.
 */
public class FlipThread extends Thread{

    private ImageView clickedCard;
    private ImageView previousCard;
    private Handler handler;
    private int delayTime = 1000;

    public FlipThread(ImageView clickedCard, ImageView previousCard) {
        this.clickedCard = clickedCard;
        this.previousCard = previousCard;
        handler = new Handler();
    }

    @Override
    public void run() {
        try {
            sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                previousCard.setImageResource(R.drawable.card_back);
                clickedCard.setImageResource(R.drawable.card_back);
            }
        });
    }
}

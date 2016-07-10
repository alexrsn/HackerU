package com.example.androidanimation;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    RelativeLayout layout;
    RelativeLayout.LayoutParams params;
    ImageView imageView;
    Handler handler = new Handler();
    boolean go = true;
    Thread thread;
    int xPos = 30;
    int yPos = 50;
    int velocityX = 13;
    int velocityY = 8;
    int maxX, maxY;
    boolean doing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ImageView myAnimated = (ImageView) findViewById(R.id.myAnimated);
        myAnimated.setBackgroundResource(R.drawable.animated);
        AnimationDrawable animationDrawable = (AnimationDrawable) myAnimated.getBackground();
        animationDrawable.start();*/

        layout = (RelativeLayout) findViewById(R.id.layout);
        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.pic1);
        params = new RelativeLayout.LayoutParams(200, 200);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        maxX = size.x - 200;
        maxY = size.y - 300;
        params.setMargins(xPos, yPos, 0, 0);
        layout.addView(imageView, params);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            params.setMargins(xPos, yPos, 0, 0);

            imageView.setLayoutParams(params);
            doing = false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (go) {
                    xPos += velocityX;
                    yPos += velocityY;
                    if (xPos >= maxX || xPos <= 0)
                        velocityX *= -1;
                    if (yPos >= maxY || yPos <= 0)
                        velocityY *= -1;
                    if (!doing) {
                        doing = true;
                        handler.post(runnable);
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        go = false;
        thread.interrupt();
        thread = null;
    }

}

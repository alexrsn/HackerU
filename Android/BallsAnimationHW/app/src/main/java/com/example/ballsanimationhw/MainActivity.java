package com.example.ballsanimationhw;

import android.app.Activity;
import android.graphics.Point;
import android.os.Handler;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static Boolean makingMove = false;
    private RelativeLayout mainLayout;
    private Handler handler;
    private ArrayList<Ball> ballArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        handler = new Handler();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Ball.maxX = size.x - Ball.BALL_SIZE;
        Ball.maxY = size.y - Ball.BALL_SIZE - 80;
        ballArrayList = new ArrayList<>();
    }

    public void mainLayoutClick(View view) {
        Ball ball = new Ball(this, mainLayout, handler, ballArrayList);
        ballArrayList.add(ball);
    }
}

package com.example.ballsanimationhw;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class Ball {
    final static int[] colors = {R.drawable.ball_black,R.drawable.ball_blue,
            R.drawable.ball_green,R.drawable.ball_red,R.drawable.ball_yellow};
    public static final int BALL_SIZE = 40;
    private ImageView ballImageView;
    private RelativeLayout.LayoutParams params;
    private Thread thread;
    private Handler handler;
    private ArrayList<Ball> ballArrayList;
    private boolean go;
    private boolean busy;
    private int xPos = 30;
    private int yPos = 30;
    private int velocityX = 10;
    private int velocityY = 10;
    public static int maxX, maxY;


    public Ball(Context context, RelativeLayout mainLayout, Handler handler, ArrayList<Ball> ballArrayList) {
        this.handler = handler;
        this.ballArrayList = ballArrayList;
        ballImageView = new ImageView(context);
        params = new RelativeLayout.LayoutParams(BALL_SIZE, BALL_SIZE);
        ballImageView.setImageResource(colors[(ballArrayList.size()%colors.length)]);
        params.setMargins(xPos, yPos, 0, 0);
        mainLayout.addView(ballImageView, params);
        startMove();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            params.setMargins(xPos, yPos, 0, 0);
            ballImageView.setLayoutParams(params);
        }
    };

    public void startMove() {
        thread = new Thread() {
            @Override
            public void run() {
                go = true;
                while (go) {
                    busy = false;
                    synchronized (MainActivity.makingMove) {
                        if (MainActivity.makingMove) {
                            busy = true;
                        } else {
                            MainActivity.makingMove = true;
                        }
                    }
                    if (!busy) {
                        for (int i = 0; i < ballArrayList.size(); i++) {
                            boolean checkTouch = Math.sqrt(Math.pow(ballArrayList.get(i).getxPos() - xPos, 2) +
                                    Math.pow(ballArrayList.get(i).getyPos() - yPos, 2)) <= BALL_SIZE;
                            if (ballArrayList.get(i).getBallImageView() == ballImageView) {
                                continue;
                            } else if (checkTouch) {
                                velocityX *= -1;
                                velocityY *= -1;
                                break;
                            }
                        }
                        xPos += velocityX;
                        yPos += velocityY;
                        if (xPos >= maxX || xPos <= 0) {
                            xPos -= velocityX;
                            yPos -= velocityY;
                            velocityX *= -1;
                        }
                        if (yPos >= maxY || yPos <= 0) {
                            xPos -= velocityX;
                            yPos -= velocityY;
                            velocityY *= -1;
                        }
                        handler.post(runnable);
                        MainActivity.makingMove = false;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        thread.start();
    }


    public void stopMove() {
        go = false;
        thread.interrupt();
        thread = null;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public ImageView getBallImageView() {
        return ballImageView;
    }
}

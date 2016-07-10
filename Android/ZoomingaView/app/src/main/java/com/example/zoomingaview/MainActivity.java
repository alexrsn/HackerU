package com.example.zoomingaview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Animator animator;
    private int duration;
    private ImageButton btnClickMe;
    private ViewGroup container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (ViewGroup) findViewById(R.id.container);
        btnClickMe = (ImageButton) findViewById(R.id.btnClickMe);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomImage();
            }
        });

        duration = getResources().getInteger(android.R.integer.config_longAnimTime);
    }

    private void zoomImage() {
        if (animator != null) {
            animator.cancel();
        }
        ImageView imgCard = (ImageView) findViewById(R.id.imgCard);
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffcet = new Point();
        btnClickMe.getGlobalVisibleRect(startBounds);
        container.getGlobalVisibleRect(finalBounds, globalOffcet);
        startBounds.offset(-globalOffcet.x, -globalOffcet.y);
        finalBounds.offset(-globalOffcet.x, -globalOffcet.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.left -= deltaHeight;
            startBounds.right += deltaHeight;
        }

        btnClickMe.setAlpha(0f);
        imgCard.setVisibility(View.VISIBLE);
        imgCard.setPivotX(0f);
        imgCard.setPivotY(0f);

        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(imgCard, View.X, startBounds.left, finalBounds.left)).
                with(ObjectAnimator.ofFloat(imgCard, View.Y, startBounds.top, finalBounds.top)).
                with(ObjectAnimator.ofFloat(imgCard, View.SCALE_X, startScale, 1f)).
                with(ObjectAnimator.ofFloat(imgCard, View.SCALE_Y, startScale, 1f));
        set.setDuration(duration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animation = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                animation = null;
            }
        });
        set.start();
        animator = set;
    }
}

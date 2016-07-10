package com.example.graphiceffects;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView imgLion, imgFish;
    private int duration;
    private boolean cardBack = true;
    private boolean front = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        imgLion = (ImageView) findViewById(R.id.imgLion);
//        imgFish = (ImageView) findViewById(R.id.imgFish);
//        imgFish.setVisibility(View.GONE);
//
//        duration = getResources().getInteger(android.R.integer.config_shortAnimTime) * 10;

        if(savedInstanceState==null){//first run of onCreate
            getFragmentManager().beginTransaction().add(R.id.layout,new CardFrontFragment()).commit();
        }

    }

    public void clickOnScreen(View view) {
        //crossFade();
        //cardFlip();
        flipCard();
    }

    private void crossFade() {
        imgFish.setAlpha(0f);
        imgFish.setVisibility(View.VISIBLE);
        imgFish.animate().alpha(1f).setDuration(duration).setListener(null);
        imgLion.animate().alpha(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imgLion.setVisibility(View.GONE);
            }
        });
    }

    private void cardFlip() {
        imgLion.animate().scaleX(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imgLion.setImageResource(cardBack ? R.drawable.card_front : R.drawable.card_back);
                cardBack = !cardBack;
                imgLion.animate().scaleX(1f).setDuration(duration).setListener(null);
            }
        });
    }

    public static class CardBackFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.card_back, container, false);
            return view;
        }
    }

    public static class CardFrontFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.card_front, container, false);
            return view;
        }
    }

    private void flipCard() {
        if (!front) {
            getFragmentManager().popBackStack();
            front = true;
            return;
        }
        front = false;
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out)
                .replace(R.id.layout, new CardBackFragment()).addToBackStack(null).commit();
    }
}

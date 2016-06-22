package com.example.servicehw;

import android.widget.TextView;

public class CounterRunnable implements Runnable{

    private int counter;
    private TextView textView;


    public CounterRunnable(int counter, TextView textView) {
        this.counter = counter;
        this.textView = textView;
    }

    @Override
    public void run() {
            textView.setText("Counter:" + counter);
    }
}

package com.example.messagehwhttpclient;

import android.widget.TextView;

public class MessageRunnable implements Runnable{

    private String message;
    private TextView textView;


    public MessageRunnable(String message, TextView textView) {
        this.message = message;
        this.textView = textView;
    }

    @Override
    public void run() {
        if(!textView.getText().equals(message))
            textView.setText("Message:" + message);
    }
}

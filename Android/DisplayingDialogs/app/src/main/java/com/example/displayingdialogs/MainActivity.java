package com.example.displayingdialogs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    LinearLayout layoutMessage;
    Button showBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutMessage = (LinearLayout) findViewById(R.id.layoutMessage);
        showBtn = (Button) findViewById(R.id.btnShowMessage);

    }

    public void btnShowMessage(View view) {
        layoutMessage.setVisibility(View.VISIBLE);
        showBtn.setEnabled(false);
    }

    public void btnCloseMessage(View view) {
        layoutMessage.setVisibility(View.GONE);
        showBtn.setEnabled(true);
    }
}

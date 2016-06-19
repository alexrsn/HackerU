package com.resin.makingyourappcallablebyothers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculateActivity extends AppCompatActivity {

    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        int x = getIntent().getIntExtra("x", 0);
        int y = getIntent().getIntExtra("y", 0);

        TextView lblResult = (TextView) findViewById(R.id.lblResult);
        lblResult.setText("result = " + (x + y));
        result = x + y;
        lblResult.setText("result = " + result);
    }

    public void btnGoBack(View view) {
        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(RESULT_OK, intent);
        finish();
    }
}

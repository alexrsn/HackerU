package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREFERENCES = "MY_PREFERENCES";
    public static final String KEY_1 = "KEY1";

    TextView lblCurrentValue;
    EditText txtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblCurrentValue = (TextView) findViewById(R.id.lblCurrentValue);
        txtInput = (EditText) findViewById(R.id.txtInput);


        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        String input = sharedPreferences.getString(KEY_1, getString(R.string.no_value));
        lblCurrentValue.setText(input);

    }

    public void btnSave(View view) {
        String input = txtInput.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(MainActivity.this, R.string.must_enter_a_value, Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_1, input);
        editor.commit();
        Toast.makeText(MainActivity.this, R.string.saved_successfully, Toast.LENGTH_SHORT).show();
        lblCurrentValue.setText(input);
    }
}

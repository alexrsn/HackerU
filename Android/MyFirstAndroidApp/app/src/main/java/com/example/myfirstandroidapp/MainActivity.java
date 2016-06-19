package com.example.myfirstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String STR_1 = "str1";
    public static final String AGE_1 = "age1";
    public static final int REQUEST_CODE = 6;
    public static Dog staticDog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ALEX", "how are you");//save to log with tag "ALEX" - 'how are you'

    }

    public void btnOnClick(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);

        //pass parameters to other activity
        //intent.putExtra("str1", "some string I want to pass");
        //intent.putExtra("age1", 25);
        Bundle extras = new Bundle();
        extras.putString(STR_1, "some string I want to pass");
        extras.putInt(AGE_1, 25);
        Dog d = new Dog("Snoopy" , 250);
        staticDog = d;
        extras.putSerializable("dog1", d);
        intent.putExtras(extras);
        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "user name: " +
                        data.getStringExtra(SecondaryActivity.USER_NAME), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "account number: " +
                        data.getIntExtra(SecondaryActivity.ACCOUNT_NUMBER, -1), Toast.LENGTH_SHORT).show();
            }
        }

    }
}


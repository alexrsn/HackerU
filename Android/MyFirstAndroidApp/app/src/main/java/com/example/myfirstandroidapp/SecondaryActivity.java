package com.example.myfirstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondaryActivity extends Activity {

    public static final String USER_NAME = "userName";
    public static final String ACCOUNT_NUMBER = "accountNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        //getIntent() returns pointer to intent that created this activity
        Bundle extras = getIntent().getExtras();
        String str1 = extras.getString(MainActivity.STR_1);
        //getInt can return default value if it gets wrong number
        int age1 = extras.getInt(MainActivity.AGE_1, -1);

        Dog d = (Dog)extras.getSerializable("dog1");
        //Dog d = MainActivity.staticDog;
        Toast.makeText(SecondaryActivity.this, "dog: " + d.get_name() + " " + d.get_age(), Toast.LENGTH_SHORT).show();

        //Toast show massage for a few seconds(LONG/SHORT) like balloon tip
        Toast.makeText(this, str1 == null ? "null" : str1, Toast.LENGTH_SHORT).show();

        //error!!! int(age1) in second parameter of Toast will call another method and will crash
        //Toast.makeText(SecondaryActivity.this, age1, Toast.LENGTH_SHORT).show();

        //Toast.makeText(SecondaryActivity.this, String.valueOf(age1), Toast.LENGTH_SHORT).show();
        //Toast.makeText(SecondaryActivity.this, Integer.toString(age1), Toast.LENGTH_SHORT).show();
        //Toast.makeText(SecondaryActivity.this, "age1 = " + age1, Toast.LENGTH_SHORT).show();

        /*
        //Intent intent = new Intent("com.example.myfirstandroidapp.ThirdActivity");
        //startActivity(intent); //if no activity found with given name the app will crash

        Intent intent = new Intent();
        intent.setAction("com.example.myfirstandroidapp.ThirdActivity");

        //Intent.createChooser will show a massage to choose app that match given name
        startActivity(Intent.createChooser(intent, "Third Activity!"));
        */


    }

    public void btnOnClick(View view) {
        Intent intent = new Intent();
        intent.putExtra(USER_NAME, "alex");
        intent.putExtra(ACCOUNT_NUMBER, 12345);
        setResult(RESULT_OK, intent);//sets values for returning them to previous activity
        finish();//close current activity
    }
}

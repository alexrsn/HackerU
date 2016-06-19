package com.example.radiobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String s = "Option ";
                /*
                RadioButton radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
                if(radioButton1.isChecked()){
                    s += "1";
                }else{
                    s += "2";
                }

                */
                switch (checkedId){
                    case R.id.radioButton1:
                        s += "1";
                        break;
                    case R.id.radioButton2:
                        s += "2";
                        break;
                }

                //radioGroup1.getCheckedRadioButtonId()

                s += " checked!";
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

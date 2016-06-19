package com.example.displayingdialogfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements InputNameDialogFragment.InputNameDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnShowInputNameDialog(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        InputNameDialogFragment dialogFragment = new InputNameDialogFragment();
        dialogFragment.setCancelable(false);//(false) - click outside of fragment will not close the fragment
        Bundle args = new Bundle();
        args.putString(InputNameDialogFragment.TITLE, "Enter name");
        dialogFragment.setArguments(args);
        dialogFragment.setInputNameDialogListener(this);
        dialogFragment.show(fragmentManager,"input dialog");//"input dialog" - is a tag of fragment
    }

    @Override
    public void onFinishInputDialog(String inputText) {
        FragmentManager fragmentManager = getFragmentManager();
        YesOrNoDialogFragment dialogFragment = new YesOrNoDialogFragment();
        dialogFragment.setCancelable(false);//(false) - click outside of fragment will not close the fragment
        Bundle args = new Bundle();
        args.putString(InputNameDialogFragment.TITLE, "Hello " + inputText + ", are you sure?");
        dialogFragment.setArguments(args);
        dialogFragment.setYesNoDialogListener(new YesOrNoDialogFragment.YesNoDialogListener() {
            @Override
            public void onFinishYesNoDialog(Boolean isYesSelected) {
                Toast.makeText(MainActivity.this, "Choosed :" + (isYesSelected == true ? "Yes" : "No"), Toast.LENGTH_SHORT).show();
            }
        });
        dialogFragment.show(fragmentManager,"yes no dialog");//"yes no dialog" - is a tag of fragment
    }



    public void btnShowYesOrNoDialog(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        YesOrNoDialogFragment dialogFragment = new YesOrNoDialogFragment();
        dialogFragment.setCancelable(false);//(false) - click outside of fragment will not close the fragment
        Bundle args = new Bundle();
        args.putString(InputNameDialogFragment.TITLE, "Are you sure");
        dialogFragment.setArguments(args);
        dialogFragment.setYesNoDialogListener(new YesOrNoDialogFragment.YesNoDialogListener() {
            @Override
            public void onFinishYesNoDialog(Boolean isYesSelected) {
                Toast.makeText(MainActivity.this, "Choosed :" + (isYesSelected == true ? "Yes" : "No"), Toast.LENGTH_SHORT).show();
            }
        });
        dialogFragment.show(fragmentManager,"yes no dialog");//"input dialog" - is a tag of fragment
    }
}

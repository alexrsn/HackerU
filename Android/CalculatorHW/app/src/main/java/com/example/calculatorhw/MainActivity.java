package com.example.calculatorhw;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    private TextView textView;
    private int operatorPos;
    private double num1, num2, result;
    private boolean isNumberPressed;
    private DecimalFormat myResultFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operatorPos = -1;
        isNumberPressed = false;
        myResultFormat = new DecimalFormat("#.##");
        textView = (TextView) findViewById(R.id.txtResult);
    }

    public void btnNumberPressed(View view) {
        isNumberPressed = true;
        textView.append(view.getTag().toString());
    }

    public void btnPlusMinusPressed(View view) {
    }

    public void btnPercentPressed(View view) {

    }

    public void btnOperatorPressed(View view) {
        if (isNumberPressed) {
            if (operatorPos == -1) {
                num1 = Double.valueOf(textView.getText().toString());
                operatorPos = textView.getText().length();
                textView.append(view.getTag().toString());
                result = num1;
            } else
                textView.setText(myResultFormat.format(result) + view.getTag().toString());
        }
    }

    public void btnPointPressed(View view) {
    }

    public void btnEqualsPressed(View view) {
        if (operatorPos != -1 && textView.getText().length() - 1 > operatorPos) {
            num2 = Double.valueOf(textView.getText().subSequence(operatorPos + 1, textView.getText().length()).toString());

            if (textView.getText().charAt(operatorPos) == '+')
                result = num1 + num2;
            else if (textView.getText().charAt(operatorPos) == '-')
                result = num1 - num2;
            else if (textView.getText().charAt(operatorPos) == 'x')
                result = num1 * num2;
            else if (textView.getText().charAt(operatorPos) == '÷') {
                if (num2 != 0)
                    result = num1 / num2;
                else
                    Toast.makeText(MainActivity.this, "Can't divide by 0!!!", Toast.LENGTH_SHORT).show();
            }
            textView.setText(myResultFormat.format(result));
            operatorPos = -1;
        }
    }

    public void btnCPressed(View view) {
        textView.setText("");
        operatorPos = -1;
        isNumberPressed = false;
    }
}

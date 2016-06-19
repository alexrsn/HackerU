package com.example.tcpclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {
    EditText txtFirstNumber,txtSecondNumber;
    TextView lblResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirstNumber = (EditText)findViewById(R.id.txtFirstNumber);
        txtSecondNumber = (EditText)findViewById(R.id.txtSecondNumber);
        lblResult = (TextView)findViewById(R.id.lblResult);
    }

    public void btnCalculate(View view) {
        String firstNumberString = txtFirstNumber.getText().toString();
        String secondNumberString = txtSecondNumber.getText().toString();
        if(firstNumberString.equals("") ||secondNumberString.equals(""))
            return;

        int firstNumber = Integer.valueOf(firstNumberString);
        int secondNumber = Integer.valueOf(secondNumberString);
        int action = -1;
        String button = ((Button)view).getText().toString();
        
        switch (button){
            case "+":
                action = 100;
                break;
            case "-":
                action = 101;
                break;
            case "*":
                action = 102;
                break;
            case "/":
                action = 103;
                break;
        }

        new AsyncTask<Integer, Void, Integer>(){

            @Override
            protected Integer doInBackground(Integer... params) {
                Integer result = null;
                int action = params[0];
                int firstNumber = params[1];
                int secondNumber = params[2];
                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    Socket clientSocket = new Socket("10.0.2.2",3000);
                    inputStream = clientSocket.getInputStream();
                    outputStream = clientSocket.getOutputStream();
                    outputStream.write(action);
                    byte[] firstNumberBytes = new byte[4];
                    ByteBuffer.wrap(firstNumberBytes).putInt(firstNumber);
                    byte[] secondNumberBytes = new byte[4];
                    ByteBuffer.wrap(secondNumberBytes).putInt(secondNumber);
                    outputStream.write(firstNumberBytes);
                    outputStream.write(secondNumberBytes);
                    byte[] response = new byte[4];
                    int actuallyRead = inputStream.read(response);
                    if(actuallyRead == 4){
                        result = ByteBuffer.wrap(response).getInt();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(outputStream!=null){
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                if(integer!=null)
                    lblResult.setText(Integer.toString(integer));
                else
                    lblResult.setText("error!");
            }
        }.execute(action,firstNumber,secondNumber);
    }
}

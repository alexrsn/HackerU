package com.example.messaginglistviewhw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayAdapter<Message> arrayAdapter;
    private List<Message> messageList;
    private EditText txtMessage;
    private EditText txtSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageList = new ArrayList<>();
        messageList.add(new Message("hi", "alex", "12:30"));
        messageList.add(new Message("hello", "alex", "12:30"));
        messageList.add(new Message("hi", "me", "12:30"));
        messageList.add(new Message("whatsapp", "moshe", "12:30"));
        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new MessageArrayAdapter(this, messageList);
        listView.setAdapter(arrayAdapter);

        txtMessage = (EditText) findViewById(R.id.txtMessage);
        txtSender = (EditText) findViewById(R.id.txtSender);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//move EditText above the keyboard

    }

    public void btnSendMessage(View view) {
        if (txtMessage.getText().toString().equals("") || txtSender.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Sender name or message is empty!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            String time = new SimpleDateFormat("HH:mm").format(new Date());
            messageList.add(new Message(txtMessage.getText().toString(), txtSender.getText().toString(),time));
            arrayAdapter.notifyDataSetChanged();
        }
    }
}

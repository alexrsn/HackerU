package com.example.callingbuiltinapps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:32.084482, 34.800709"));
        startActivity(intent);
        */

        /*
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.whatsapp"));
        startActivity(intent);
        */

        /*
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {"obama@gmail.com", "puttin@gmail.com"};
        String[] cc = {"michel@gmail.com", "ludmila@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, "world peace");
        intent.putExtra(Intent.EXTRA_TEXT, "yeah right...");
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Email"));
        */

        /*
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "some subject...");
        intent.putExtra(Intent.EXTRA_TEXT, "some text...");
        startActivity(Intent.createChooser(intent, "Message"));
        */


        


    }
}

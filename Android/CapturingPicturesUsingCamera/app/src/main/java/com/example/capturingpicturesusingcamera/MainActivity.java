package com.example.capturingpicturesusingcamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    static final int TAKE_PICTURE = 1;
    Uri outputFileUri;
    File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            outputFileUri = Uri.parse(savedInstanceState.getString("outputFileUri"));
            file = new File(savedInstanceState.getString("file"));
        }
    }

    public void btnTakePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        //File externalStorageDirectory = getExternalCacheDir();
        Log.d("Alex", externalStorageDirectory.toString());
        file = new File(externalStorageDirectory, "MyPhoto.jpg");
        outputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, TAKE_PICTURE);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("outputFileUri", outputFileUri.toString());
        outState.putString("file", file.toString());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
            Log.d("Alex", "file saved in " + outputFileUri.toString());
        }
        if (file.exists()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            File output = new File(externalStorageDirectory, "MyPhoto1.jpg");
            try {
                inputStream = new FileInputStream(file);
                outputStream = new FileOutputStream(output);
                byte[] buffer = new byte[1024];
                int actuallyRead;
                while ((actuallyRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, actuallyRead);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null)
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (outputStream != null)
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            Bitmap bitmap = BitmapFactory.decodeFile(output.toString());
            ImageView photo = (ImageView) findViewById(R.id.photo);
            photo.setImageBitmap(bitmap);
        }
    }
}
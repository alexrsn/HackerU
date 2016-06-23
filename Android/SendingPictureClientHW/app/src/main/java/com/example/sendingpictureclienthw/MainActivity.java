package com.example.sendingpictureclienthw;

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

public class MainActivity extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    private Uri newPhotoUri;
    private File newPhotoFile;
    private File serverPhotoFile;
    private File externalStorageDir;
    private ImageView newPhotoImageView;
    private ImageView currentPhotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newPhotoImageView = (ImageView)findViewById(R.id.newPhoto);
        currentPhotoImageView = (ImageView)findViewById(R.id.currentPhoto);

        externalStorageDir = Environment.getExternalStorageDirectory();
        newPhotoFile = new File(externalStorageDir,"NewPhoto.jpg");
        serverPhotoFile = new File(externalStorageDir,"ServerPhoto.jpg");

    }


    public void btnTakePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        newPhotoUri = Uri.fromFile(newPhotoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,newPhotoUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    public void btnUploadPhoto(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
            Log.d("Alex", "file saved in: " + newPhotoUri.toString());

            Bitmap newPhotoBitmap = BitmapFactory.decodeFile(newPhotoFile.toString());
            newPhotoImageView.setImageBitmap(newPhotoBitmap);
        }
    }
}

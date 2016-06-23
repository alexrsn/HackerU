package com.example.capturingpicturesusingcamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by resin on 23/06/2016.
 */
public class PictureRunnable implements Runnable {

    private ImageView serverPhoto;
    private File serverPhotoFile;

    public PictureRunnable(ImageView serverPhoto, File serverPhotoFile) {
        this.serverPhoto = serverPhoto;
        this.serverPhotoFile = serverPhotoFile;
    }

    @Override
    public void run() {
        Bitmap bitmap = BitmapFactory.decodeFile(serverPhotoFile.toString());
        serverPhoto.setImageBitmap(bitmap);
    }
}

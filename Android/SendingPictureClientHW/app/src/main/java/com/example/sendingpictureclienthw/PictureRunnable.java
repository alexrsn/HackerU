package com.example.sendingpictureclienthw;

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
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile(serverPhotoFile.toString(),options);
        serverPhoto.setImageBitmap(bitmap);
    }
}

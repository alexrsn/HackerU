package com.example.alex.homismarttest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by resin on 06/11/2016.
 */

public class GetImageThread extends Thread {

    private Context context;
    private Handler handler;
    private URL url;
    private ImageView imageView;

    public GetImageThread(Context context, Handler handler, URL url, ImageView imageView) {
        this.context = context;
        this.handler = handler;
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        try {
            String fileName = saveImageFromUrl(context, url);
            setImageFromFile(context, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String saveImageFromUrl(Context context, URL url) throws IOException {

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            String outputFileName = "image_" + System.currentTimeMillis() + ".png";

            inputStream = url.openConnection().getInputStream();
            fileOutputStream = context.openFileOutput(outputFileName, Context.MODE_PRIVATE);

            int actuallyRead;
            byte[] buffer = new byte[1024];
            while ((actuallyRead = inputStream.read(buffer)) != -1)
                fileOutputStream.write(buffer, 0, actuallyRead);

            return outputFileName;

        } finally {
            if (fileOutputStream != null)
                fileOutputStream.close();
            if (inputStream != null)
                inputStream.close();
        }
    }

    private void setImageFromFile(Context context, String fileName) throws IOException {
        Bitmap bitmap = null;
        FileInputStream fileInputStream;
        try {
            fileInputStream = context.openFileInput(fileName);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
            final Bitmap finalBitmap = bitmap;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(finalBitmap);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

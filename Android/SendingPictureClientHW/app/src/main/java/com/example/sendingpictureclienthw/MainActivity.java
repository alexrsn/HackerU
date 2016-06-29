package com.example.sendingpictureclienthw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private static final int TAKE_PICTURE = 1;
    //private static final String IP_ADDRESS = "192.168.14.55";
    private static final String IP_ADDRESS = "192.168.43.215";
    private Uri newPhotoUri;
    private File newPhotoFile;
    private File serverPhotoFile;
    private ImageView newPhotoImageView;
    private ImageView serverPhotoImageView;
    private Handler handler;
    private boolean exitFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newPhotoImageView = (ImageView)findViewById(R.id.newPhoto);
        serverPhotoImageView = (ImageView)findViewById(R.id.serverPhoto);

        handler = new Handler();
        File externalStorageDir = Environment.getExternalStorageDirectory();
        newPhotoFile = new File(externalStorageDir,"NewPhoto.jpg");
        serverPhotoFile = new File(externalStorageDir,"ServerPhoto.jpg");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!exitFlag) {
                    getPhotoFromServer();
                    handler.post(new PictureRunnable(serverPhotoImageView, serverPhotoFile));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }

    public void getPhotoFromServer(){
        URL url;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            url = new URL("http://" + IP_ADDRESS + ":8080/PictureDownloadServlet");
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type","text/plain");
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            outputStream = new FileOutputStream(serverPhotoFile);
            byte[] buffer = new byte[1024];
            int actuallyRead;
            while ((actuallyRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, actuallyRead);
            }
            inputStream.close();
            outputStream.close();
            urlConnection.disconnect();
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
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
    }

    public boolean sendPhotoToServer(){
        URL url;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            url = new URL("http://" + IP_ADDRESS + ":8080/PictureUploadServlet");
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data");
            urlConnection.connect();
            fileInputStream = new FileInputStream(newPhotoFile);
            outputStream = urlConnection.getOutputStream();
            byte[] buffer = new byte[1024];
            int actuallyRead;
            while ((actuallyRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, actuallyRead);
            }
            outputStream.close();
            inputStream = urlConnection.getInputStream();
            inputStream.read();
            inputStream.close();
            fileInputStream.close();
            urlConnection.disconnect();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        return true;
    }

    public void btnTakePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        newPhotoUri = Uri.fromFile(newPhotoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,newPhotoUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    public void btnUploadPhoto(View view) {
        new AsyncTask<Void,Void,Boolean>(){
            @Override
            protected Boolean doInBackground(Void... params) {
                if (!newPhotoFile.exists()) {
                    Toast.makeText(MainActivity.this, "File not found!!", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return sendPhotoToServer();
            }

            @Override
            protected void onPostExecute(Boolean isSent) {
                if(isSent) {
                    Bitmap serverPhotoBitmap = BitmapFactory.decodeFile(serverPhotoFile.toString());
                    serverPhotoImageView.setImageBitmap(serverPhotoBitmap);
                }else
                    Toast.makeText(MainActivity.this, "Photo not uploaded!!", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public void btnExit(View view) {
        exitFlag = true;
        finish();
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

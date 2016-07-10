package com.example.cameraimageshareclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/*
 Write an app that allows taking a picture using the camera,
 and sharing it with other users of the app.
 The user will click on a button that will allow taking a photo,
 and when the user is done, a button "share" will send the image,
 to the server.
 The app always shows the last image sent to the server by any user.
 So the activity has: a button "Take Photo", an ImageView and
 a "Share" button.
 */


public class MainActivity extends AppCompatActivity {


    public static final int TAKE_PICTURE = 1;
    public static final String MY_IMAGE_FILE_NAME = "MyImage.jpg";
    public static final String SHARED_IMAGE_FILE_NAME = "SharedImage.jpg";
    public static final int SEND_IMAGE = 100;
    public static final int GET_IMAGE = 101;
    public static final int OKAY = 1;
    public static final int FAILURE = 2;
    public static final int BUSY = 3;
    public static final int ALREADY_UP_TO_DATE = 4;
    public static final int HAS_NEW_IMAGE = 5;
    //public static final String SERVER_IP = "10.10.10.196";
    public static final String SERVER_IP = "192.168.43.215";
    public static final int SERVER_PORT = 3000;

    private CheckForNewImageThread checkForNewImageThread;
    private Handler handler = new Handler();
    private Uri outputFileUri;
    private File file;

    private Button btnShare;
    private ImageView imgShared, imgMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShare = (Button) findViewById(R.id.btnShare);
        imgShared = (ImageView) findViewById(R.id.imgShared);
        imgMine = (ImageView) findViewById(R.id.imgMine);
    }

    public void btnTakePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        file = new File(externalStorageDirectory, MY_IMAGE_FILE_NAME);
        outputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
            Log.d("Alex", "file saved in " + outputFileUri.toString());
            btnShare.setEnabled(true);
        }
        if (file.exists()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            File output = new File(externalStorageDirectory, MY_IMAGE_FILE_NAME);
            Bitmap bitmap = BitmapFactory.decodeFile(output.toString());
            imgMine.setImageBitmap(bitmap);
        }
    }

    public void btnShare(View view) {
        if (!file.exists())
            return;
        btnShare.setEnabled(false);
        new AsyncTask<Void, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                Socket socket = null;
                OutputStream outputStream = null;
                InputStream inputStream = null;
                FileInputStream fileInputStream = null;
                boolean inputStreamClosed = false, outputStreamClosed = false;
                try {
                    socket = new Socket(SERVER_IP, SERVER_PORT);
                    outputStream = socket.getOutputStream();
                    inputStream = socket.getInputStream();
                    outputStream.write(SEND_IMAGE);
                    int okay = inputStream.read();
                    if (okay == OKAY) {
                        File externalStorageDirectory = Environment.getExternalStorageDirectory();
                        File myFile = new File(externalStorageDirectory,MY_IMAGE_FILE_NAME);
                        fileInputStream = new FileInputStream(myFile);
                        byte[] buffer = new byte[1024];
                        int actuallyRead;
                        while ((actuallyRead = fileInputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, actuallyRead);
                        }
                        outputStreamClosed = true;
                        outputStream.close();
                        int sendResult = inputStream.read();
                        if (sendResult == OKAY) {
                            return OKAY;
                        }
                    } else if (okay == BUSY) {
                        return BUSY;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Alex",e.toString());
                } finally {
                    if (!inputStreamClosed && inputStream != null)
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if (fileInputStream != null)
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    if (!outputStreamClosed && outputStream != null)
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    try {
                        if (socket != null)
                            socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return FAILURE;
            }

            @Override
            protected void onPostExecute(Integer sendResult) {
                String message = "";
                switch (sendResult) {
                    case FAILURE:
                        message = "failure";
                        break;
                    case OKAY:
                        message = "okay";
                        break;
                    case BUSY:
                        message = "busy";
                        break;
                }
                Toast.makeText(MainActivity.this, "result " + message,
                        Toast.LENGTH_SHORT).show();

            }
        }.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForNewImageThread = new CheckForNewImageThread(handler, new Runnable() {
            @Override
            public void run() {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File sharedImageFile = new File(externalStorageDirectory,SHARED_IMAGE_FILE_NAME);
                if(sharedImageFile.exists()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bitmap = BitmapFactory.decodeFile(sharedImageFile.toString(),options);
                    imgShared.setImageBitmap(bitmap);
                }
            }
        });
        checkForNewImageThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkForNewImageThread.stopChecking();
        checkForNewImageThread = null;
    }
}
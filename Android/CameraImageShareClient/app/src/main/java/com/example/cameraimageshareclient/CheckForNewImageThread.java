package com.example.cameraimageshareclient;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by resin on 26/06/2016.
 */
public class CheckForNewImageThread extends Thread {
    private boolean go;
    private int version = 0;
    private Handler handler;
    private Runnable runnable;

    public CheckForNewImageThread(Handler handler, Runnable runnable) {
        this.handler = handler;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        go = true;
        while(go){
            Socket socket = null;
            OutputStream outputStream = null;
            InputStream inputStream = null;
            boolean inputStreamClosed = false, outputStreamClosed = false;
            FileOutputStream fileOutputStream = null;
            boolean fileOutputStreamClosed = false;
            boolean success = false;
            try {
                socket = new Socket(MainActivity.SERVER_IP, MainActivity.SERVER_PORT);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                outputStream.write(MainActivity.GET_IMAGE);
                byte[] versionBytes = new byte[4];
                ByteBuffer.wrap(versionBytes).putInt(version);
                outputStream.write(versionBytes);
                int hasNewImage = inputStream.read();
                if(hasNewImage == MainActivity.HAS_NEW_IMAGE){
                    int actuallyRead = inputStream.read(versionBytes);
                    if(actuallyRead == 4){
                        version = ByteBuffer.wrap(versionBytes).getInt();
                        File externalStorageDirectory = Environment.getExternalStorageDirectory();
                        File sharedFile = new File(externalStorageDirectory,MainActivity.SHARED_IMAGE_FILE_NAME);
                        fileOutputStream = new FileOutputStream(sharedFile);
                        byte[] buffer = new byte[1024];
                        while((actuallyRead = inputStream.read(buffer)) != -1){
                            fileOutputStream.write(buffer, 0, actuallyRead);
                        }
                        fileOutputStream.close();
                        fileOutputStreamClosed = true;
                        success = true;
                    }
                }else if(hasNewImage == MainActivity.ALREADY_UP_TO_DATE){

                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(!inputStreamClosed && inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(!outputStreamClosed && outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(!fileOutputStreamClosed && fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(success && runnable != null){
                if(handler != null)
                    handler.post(runnable);
                else
                    runnable.run();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopChecking(){
        go = false;
        this.interrupt();
    }
}
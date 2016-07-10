package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by resin on 26/06/2016.
 */
public class ClientThread extends Thread {
    public static final int SEND_IMAGE = 100;
    public static final int GET_IMAGE = 101;
    public static final String PATH_TO_IMAGE_FILE = "C:\\test\\photo.jpg";
    public static final int OKAY = 1;
    public static final int FAILURE = 2;
    public static final int BUSY = 3;
    public static final int ALREADY_UP_TO_DATE = 4;
    public static final int HAS_NEW_IMAGE = 5;
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    boolean inputStreamClosed = false, outputStreamClosed = false;
    private Lock lock;

    public ClientThread(Socket clientSocket, Lock lock) {
        this.clientSocket = clientSocket;
        this.lock = lock;
    }

    @Override
    public void run() {

        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action = inputStream.read();
            switch (action) {
                case SEND_IMAGE:
                    sendImage();
                    break;
                case GET_IMAGE:
                    getImage();
                    break;
            }
            inputStream.close();
            inputStreamClosed = true;
            outputStream.close();
            outputStreamClosed = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!inputStreamClosed && inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!outputStreamClosed && outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void sendImage() throws IOException {
        boolean busy = false;
        synchronized (lock) {
            if (lock.lock) {
                busy = true;
            } else {
                lock.lock = true;
            }
        }
        if (busy) {
            outputStream.write(BUSY);
            return;
        } else {
            outputStream.write(OKAY);
        }

        File f = new File(PATH_TO_IMAGE_FILE);
        OutputStream fileOutputStream = null;
        boolean fileOutputStreamClosed = false;
        boolean success = false;
        try {
            fileOutputStream = new FileOutputStream(f);
            int actuallyRead;
            byte[] buffer = new byte[1024];
            while ((actuallyRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, actuallyRead);
            }
            fileOutputStream.close();
            fileOutputStreamClosed = true;
            success = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        outputStream.write(success ? OKAY : FAILURE);
        lock.lock = false;
        lock.imageVersion++; //notice that version increments on failure too
    }

    private void getImage() throws IOException {
        byte[] versionBytes = new byte[4];
        int actuallyRead = inputStream.read(versionBytes);
        if (actuallyRead != 4) {
            outputStream.write(FAILURE);
            return;
        }
        int clientVersion = ByteBuffer.wrap(versionBytes).getInt();
        if (clientVersion < lock.imageVersion) {
            outputStream.write(HAS_NEW_IMAGE);
            ByteBuffer.wrap(versionBytes).putInt(lock.imageVersion);
            outputStream.write(versionBytes);
            File f = new File(PATH_TO_IMAGE_FILE);
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                while ((actuallyRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, actuallyRead);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null)
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        } else {
            outputStream.write(ALREADY_UP_TO_DATE);
        }

    }
}
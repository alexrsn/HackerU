package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        File f = new File("C:\\test\\alex.txt");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            /*inputStream = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            int actuallyRead = inputStream.read(buffer);
            if(actuallyRead != -1){
                String s = new String(buffer, 0, actuallyRead);
                System.out.println(s);
            }
            inputStream.close();*/
            outputStream = new FileOutputStream(f);
            outputStream.write("hello alex".getBytes());
            outputStream.close();
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

    }
}
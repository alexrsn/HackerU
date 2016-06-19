package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientThread extends Thread {

    public static final int ADD = 100;
    public static final int SUBTRACT = 101;
    public static final int MULTIPLY = 102;
    public static final int DIVIDE = 103;
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean inputStreamClosed = false, outputStreamClosed = false;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action = inputStream.read();
            switch (action) {
                case ADD:
                    add();
                    break;
                case SUBTRACT:
                    subtract();
                    break;
                case MULTIPLY:
                    multiply();
                    break;
                case DIVIDE:
                    divide();
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
            if ((!outputStreamClosed && outputStream != null)) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void add() throws IOException {
        TwoNumbers twoNumbers = readTwoNumbers();
        if (twoNumbers == null)
            return;
        int result = twoNumbers.firstNumber + twoNumbers.secondNumber;
        byte[] resultBytes = new byte[4];
        ByteBuffer.wrap(resultBytes).putInt(result);
        outputStream.write(resultBytes);
    }

    private void subtract() throws IOException {
        TwoNumbers twoNumbers = readTwoNumbers();
        if (twoNumbers == null)
            return;
        int result = twoNumbers.firstNumber - twoNumbers.secondNumber;
        byte[] resultBytes = new byte[4];
        ByteBuffer.wrap(resultBytes).putInt(result);
        outputStream.write(resultBytes);
    }

    private void multiply() throws IOException {
        TwoNumbers twoNumbers = readTwoNumbers();
        if (twoNumbers == null)
            return;
        int result = twoNumbers.firstNumber * twoNumbers.secondNumber;
        byte[] resultBytes = new byte[4];
        ByteBuffer.wrap(resultBytes).putInt(result);
        outputStream.write(resultBytes);
    }

    private void divide() throws IOException {
        TwoNumbers twoNumbers = readTwoNumbers();
        if (twoNumbers == null)
            return;
        if (twoNumbers.secondNumber == 0)
            return;
        int result = twoNumbers.firstNumber / twoNumbers.secondNumber;
        byte[] resultBytes = new byte[4];
        ByteBuffer.wrap(resultBytes).putInt(result);
        outputStream.write(resultBytes);
    }

    static class TwoNumbers {
        int firstNumber, secondNumber;

        public TwoNumbers(int firstNumber, int secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }
    }

    private TwoNumbers readTwoNumbers() throws IOException {
        byte[] buffer = new byte[4];
        int actuallyRead = inputStream.read(buffer);
        if (actuallyRead != 4)
            return null;
        int firstNumber = ByteBuffer.wrap(buffer).getInt();
        actuallyRead = inputStream.read(buffer);
        if (actuallyRead != 4)
            return null;
        int secondNumber = ByteBuffer.wrap(buffer).getInt();
        return new TwoNumbers(firstNumber, secondNumber);
    }

}

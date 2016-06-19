package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by resin on 01/06/2016.
 */
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        InputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[1024];
        int actuallyRead;
        StringBuilder stringBuilder = new StringBuilder();
        while ((actuallyRead = inputStream.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, actuallyRead));
        }
        inputStream.close();
        String messageFromClient = stringBuilder.toString();
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(calculate(messageFromClient).getBytes());
        outputStream.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.getWriter().write(calculate(request.getQueryString()));
    }

    private String calculate(String querySting) {
        int answer = 0;
        if (querySting == null)
            return "empty query string!";
        String[] keyValuePairs = querySting.split("&");
        String firstNumberString = null, secondNumberString = null, operatorString = null;
        if (keyValuePairs.length != 3)
            return "wrong number of parameters";
        for (String keyValuePair : keyValuePairs) {
            String[] keyAndValue = keyValuePair.split("=");
            if (keyAndValue.length != 2)
                return "wrong key=value pair";
            switch (keyAndValue[0]) {
                case "firstNumber":
                    firstNumberString = keyAndValue[1];
                    break;
                case "secondNumber":
                    secondNumberString = keyAndValue[1];
                    break;
                case "operator":
                    operatorString = keyAndValue[1];
                    break;
            }
        }
        if (firstNumberString == null || secondNumberString == null || operatorString == null)
            return "wrong parameter";
        Integer num1, num2;
        num1 = stringToInteger(firstNumberString);
        num2 = stringToInteger(secondNumberString);
        if (num1 == null || num2 == null)
            return "not a number";
        char operator = operatorString.charAt(0);
        switch (operator) {
            case '+':
                answer = num1 + num2;
                break;
            case '-':
                answer = num1 - num2;
                break;
            case '*':
                answer = num1 * num2;
                break;
            case '/':
                if (num2 == 0)
                    return "error can't divide by 0";
                answer = num1 / num2;
                break;
            default:
                return "error wrong operator";
        }
        return String.valueOf(answer);
    }

    private Integer stringToInteger(String str) {
        int length = str.length();
        if (str == null || length == 0)
            return null;
        int result = 0;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c >= 48 && c <= 57) {
                int digit = c - 48;
                result *= 10;
                result += digit;
            } else
                return null;
        }
        return result;
    }
}





package com.company;

public class Main {

    public static void main(String[] args) {
	    System.out.println("welcome to java");
        int x = 5;
        x = 7;
        System.out.println(x);
        System.out.println("x");
        int y = 4;
        y = x + 3;
        x = x + 3;
        x += 3;
        x++;
        ++x;
        x = 9;
        y = x++;
        System.out.println(x);
        System.out.println(y);

        int z = 5; //4 bytes -2.1B to 2.1B
        byte b = 7; //1 byte -128 to 127
        short s = 10; //2 bytes  65536  -32768 to 32767
        long l = 123L; //8 bytes -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        float f = 124.25f;//4 bytes
        double d = 123.3545354;//8 bytes
        char c = 'f';//1 byte
        boolean bool = true;//1 bit

        x = 2 - 3;// = -1
        x = 2 * 3;// = 6
        x = 2 / 3;// = 0
        x = 2 % 3;// = 2
        x = 3 % 2;// = 1
        x = 2 + 3 + 4;// = 9
        x = 2 + 3 * 4;// = 14
        x = (2 + 3) * 4;// = 20

        bool = 5 < 6;



    }
}

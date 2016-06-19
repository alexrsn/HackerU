package com.company;

public class Main {

    public static void main(String[] args) {
        // & - bitwise and operator  (0011 and 0110 -> 0010  3 & 6 = 4)
        System.out.println(3&6);
        // | - bitwise or operator  (0011 or 0110 -> 0111  3 | 6 = 7)
        System.out.println(3|6);
        // ^ - bitwise xor operator  (0011 xor 0110 -> 0101  3 ^ 6 = 5)
        System.out.println(3^6);
        // << - bitwise shift left operator  (0011 << 1 -> 0110  3 << 1 = 6)
        System.out.println(3<<1);
        // >> - bitwise shift right operator  (1100 >> 2 -> 0011  12 >> 1 = 3)
        System.out.println(12>>2);


    }
}

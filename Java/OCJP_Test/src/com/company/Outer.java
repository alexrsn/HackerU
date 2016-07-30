package com.company;

/**
 * Created by resin on 10/07/2016.
 */
public class Outer{
    private int mem = 10;
    class Inner{
        private int imem = new Outer().mem;
    }

    public static void main(String[] strings) {
        System.out.println(new Outer().new Inner().imem);
    }
}
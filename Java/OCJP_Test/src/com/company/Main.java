package com.company;

public class Main {

    public static void main(String[] args) {
        //test();
    }

//    public static void test() {
//        int i = 012;
//
//        if (i == 10.0) {
//            System.out.println("true");
//        }
//    }


//    public static void test() {
//        Number[] numbers = new Number[4];
//        numbers[0] = new Number(0);
//    }

//    public static void test() {
//        StringBuffer strBuffer = new StringBuffer("This, that, etc.!");
//        System.out.println(strBuffer.replace(12,15,"etcetera"));
//    }

//    public static void test() {
//        Object nullObj = null;
//        StringBuffer strBuffer = new StringBuffer(10);
//
//        strBuffer.append("hello ");
//        strBuffer.append("world ");
//        strBuffer.append(nullObj);
//        strBuffer.insert(11, '!');
//        System.out.println(strBuffer);
//    }

//    public static void test() {
//        Boolean b = null;
//        System.out.println(b ? true : false);
//    }


}
//class Base {
//    public Base(){
//        System.out.println("Base");
//    }
//}
//
//class Derived extends Base{
//    public Derived(){
//        System.out.println("Derived");
//    }
//}
//
//class DeriDerived extends Derived{
//    public DeriDerived(){
//        System.out.println("DeriDerived");
//    }
//}
//
//class Test{
//    public static void main(String[] args) {
//        Derived b = new DeriDerived();
//    }
//}


class Point{
    private int x=0,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Point(){
        this(0, 0);
    }
}


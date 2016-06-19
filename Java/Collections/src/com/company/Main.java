package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        MyArray arr = new MyArray();
        for (int i = 0; i < 10; i++) {
            arr.add(i + 1);
        }
        //System.out.println(arr);
        //arr.add(8,1);
        //arr.add(99,10);
        //System.out.println(arr);
        //MyArray arr1 = new MyArray();
        //arr1.add(8);
        //arr1.add(6);
        //arr1.add(7);
        //arr1.add(6);
        //arr1.add(5);
        //arr1.add(9);
        //arr1.add(arr);
        //System.out.println(arr1);
        //System.out.println(arr);
        //arr.clear();
        //System.out.println(arr.contains(7));
        //System.out.println(arr.get(5));
        //System.out.println(arr.get(50));
        //System.out.println(arr.indexOf(3));
        //System.out.println(arr.lastIndexOf(7));
        //System.out.println(arr.isEmpty());
        //arr.remove(6);
        //arr1.removeAll(6);
        //System.out.println(arr.size());
        /*
        int[] tempArr = arr.toArray();
        for (int i = 0; i < tempArr.length; i++) {
            System.out.println(tempArr[i]);
        }
        */
        //System.out.println(arr1);
        // System.out.println(arr1.subList(4,8));


        MyArray2 myArray2 = new MyArray2();
        myArray2.add(14);
        myArray2.add(20);
        myArray2.add(30);
        myArray2.add(20);
        myArray2.add(90);

        //System.out.println(myArray2.contains(90));
        //System.out.println(myArray2.get(3));
        //System.out.println(myArray2.get(50));
        //System.out.println(myArray2.indexOf(30));
        //System.out.println(myArray2.lastIndexOf(20));
        //System.out.println(myArray2.isEmpty());
        //myArray2.remove(20);
        //myArray2.remove(20);
        //myArray2.remove(20);
        //myArray2.removeAll(200);

        //System.out.println(myArray2);

        //System.out.println(myArray2.size());
        /*
        int[] tempArr = myArray2.toArray();
        for (int i = 0; i < tempArr.length; i++) {
            System.out.println(tempArr[i]);
        }
        */
        //System.out.println(myArray2);
        //System.out.println(myArray2.subList(2, 4));

        //myArray2.set(45,0);
        //System.out.println(myArray2);

        MyArrayGeneric2 myArrayGeneric2 = new MyArrayGeneric2();
        myArrayGeneric2.add(14);
        myArrayGeneric2.add(20);
        myArrayGeneric2.add(30);
        myArrayGeneric2.add(20);
        myArrayGeneric2.add(90);

        //System.out.println(myArrayGeneric2.contains(90));
        //System.out.println(myArrayGeneric2.get(3));
        //System.out.println(myArrayGeneric2.get(50));
        //System.out.println(myArrayGeneric2.indexOf(30));
        //System.out.println(myArrayGeneric2.lastIndexOf(20));
        //System.out.println(myArrayGeneric2.isEmpty());
        //myArrayGeneric2.remove(20);
        //myArrayGeneric2.remove(20);
        //myArrayGeneric2.remove(20);
        //myArrayGeneric2.removeAll(20);

        //System.out.println(myArrayGeneric2.size());
        /*
        Object[] tempArr = myArrayGeneric2.toArray();
        for (int i = 0; i < tempArr.length; i++) {
            System.out.println(tempArr[i]);
        }
        */
        //System.out.println(myArrayGeneric2);
        //System.out.println(myArrayGeneric2.subList(2, 4));

        //myArrayGeneric2.set(45,3);
        //System.out.println(myArrayGeneric2);

        //List<Dog> dogs = new ArrayList<>();

        //Collections:
        //a list of interfaces in Java to deal with collections
        // * Iterable - for each
        // * Collection - very general collection
        // * List - accessing elements by index
        // * Set, SortedSet, NavigableSet, Queue, Deque -  doesn't allow duplicates
        //   Set - doesn't allow duplicates
        //   SortedSet - sorted
        //   NavigableSet - allows search
        //   Queue - FIFO (First In First Out)
        //   Deque - LIFO (Last In First Out)
        // * Map, SortedMap, NavigableMap - has key-value sets
        // * Iterator, List Iterator - like Iterable but also allows moving backwards

        // A list of concrete collection classes:
        // * ArrayList - resizeable array
        // * LinkedList - doubly-linked list data structure
        // * HashSet - implements Set.
        // * TreeSet - like HashSet but sorted
        // * HashMap - like HashSet but key-value pairs
        // * TreeMap - like HashMap but sorted
        // * PriorityQueue - is queue that is not necessarily FIFO because each object may have a priority
        // *

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new LinkedList<String>();

        for (int i = 0; i < 1000000; i++) {
            String s = String.valueOf(i);
            list1.add(s);
            list2.add(s);
        }

        String s1;
        long t1, t2, deltaT;
        t1 = System.nanoTime();
        //s1 = list1.get(967890);
        list1.remove(15);
        t2 = System.nanoTime();
        deltaT = t2 - t1;
        //System.out.println("s1: " + s1);
        System.out.println("get of arraylist took: " + deltaT);


        t1 = System.nanoTime();
        list2.remove(15);
        //s1 = list2.get(967890);
        t2 = System.nanoTime();
        deltaT = t2 - t1;
        //System.out.println("s1: " + s1);
        System.out.println("get of linked list took: " + deltaT);

        MyTime myTime = new MyTime();
        System.out.println(myTime);

    }
}

class Dog {

}

class MyTime implements Comparable<MyTime> {

    private final long timeStamp;

    public MyTime() {
        timeStamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        int hour = (int) ((timeStamp / 1000L / 60L / 60L) % 24L) + 2;
        int minute = (int) ((timeStamp / 1000L / 60L) % 60L);
        int second = (int) ((timeStamp / 1000L) % 60L);
        return (hour > 10 ? hour : "0" + hour) + ":" + (minute > 10 ? minute : "0" + minute) + ":" + (second > 10 ? second : "0" + second);
    }

    @Override
    public int compareTo(MyTime o) {
        return (int) (o.timeStamp - this.timeStamp);
    }
}
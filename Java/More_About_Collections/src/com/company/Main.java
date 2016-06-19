package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
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


        Set<String> mySet = new HashSet<>();
        mySet.add("hello");
        mySet.add("world");
        mySet.add("hello");
        //System.out.println("mySet has " + mySet.size() + " elements.");


        Dog d1 = new Dog("Snoopy");
        Dog d2 = new Dog("Snoopy");
        Set<Dog> dogs = new HashSet<>();
        //dogs.add(d1);
        //dogs.add(d2);
        //System.out.println("dogs has " + dogs.size() + " elements");

        Integer i = 8; //this calls boxing in java
        int x = i; //this calls unboxing in java

        //Map<KEY, VALUE> myMap = new HashMap<>();
        Map<Integer, String> myMap = new HashMap<>();
        Integer key1 = new Integer(8);
        String value1 = "hello";
        Integer key2 = new Integer(40);
        String value2 = "world";
        myMap.put(key1, value1);
        myMap.put(key2, value2);
        //myMap.put(key1,"bye"); //if key exist -> replace value
        Set<Integer> keys = myMap.keySet();
        for (Integer key : keys) {
            // System.out.println(myMap.get(key));
        }

        Point p = new Point(17, 6);
        System.out.println(p.hashCode());



    }
}

class Dog {
    String name;
    int age;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        System.out.println("in hashCode of Dog " + this.name);
        int nameHashCode = name.hashCode();
        return (7 * nameHashCode) ^ (11 * age) ^ (53 * age);
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("in equals of Dog " + this.name);
        if (obj == null)
            return false;
        if (obj == this)
            return false;
        if (obj instanceof Dog) {
            Dog other = (Dog) obj;
            return this.name.equals(other.name);
        }
        return false;
    }
}

class Point {
    private int xPos, yPos;

    public Point(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public int getY() {
        return yPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public int hashCode() {
        return (7 * xPos) ^ (11 * yPos) ^ (53 * yPos);//need to use prime numbers(3,7,11,...) and bitwise operators to be unique
    }
}
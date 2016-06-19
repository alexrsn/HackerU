package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Boat<Integer> myBoat = new Boat<>();//in java 6 and older must be type in <>
        //List<Integer> myList = new List<Integer>(); //you may not instantiate an interface

        //List<Number> intList = new ArrayList<Integer>(); //subclassing doesn't work in generics

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(new Integer(12143));

        List<Dog> dogs = new ArrayList<>();
        Dog d = new Dog("Snoopy");
        dogs.add(d);
        List<?> list;
        list = dogs;
        //list.add(d);//won't work because of <?>
        ((List<Dog>)list).add(d);

        List<? extends Number> intList1 = new ArrayList<Integer>();
        Number n = new Integer(123);
        //intList.add(n);//won't work because of <?>
        Number n2 = intList.get(0);



    }
}

class Boat<T>{
    T passenger1,passenger2;
}

interface A{
}
class B implements A{
}
class Dog{
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog: " + name;
    }
}
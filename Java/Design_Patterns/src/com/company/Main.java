package com.company;

public class Main {

    public static void main(String[] args) {
        Dog d = Dog.getDog();
        //Dog d = new Dog(); //won't work in Singleton
        Dog d2 = Dog.getDog();
    }
}


//Singleton - only one object of this class can be created,other
class Dog {

    private static Dog d;

    private Dog() {

    }

    public static Dog getDog() {
        if (Dog.d == null) {
            Dog.d = new Dog();
        }
        return Dog.d;
    }
}


//Object pool - recycling objects
class Cat {

    String name;
    private int index;

    private static Cat[] cats;
    private static boolean[] inUse;

    static { //static constructor
        cats = new Cat[10];
        inUse = new boolean[cats.length];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(i);
        }
    }

    private Cat(int index) {
        this.index=index;
    }

    public static Cat getCat() {
        Cat c = null;
        for (int i = 0; i < cats.length; i++) {
            if (inUse[i] == false) {
                c = cats[i];
                inUse[i] = true;
                break;
            }
        }
        return c;
    }

    public static void disposeCat(Cat c) {
        /*
        for (int i = 0; i < cats.length; i++) {
            if (cats[i] == c) {
                inUse[i] = false;
                c.name = null;
                break;
            }
        }
        */
        inUse[c.index] = false;
        c.name=null;
    }
}
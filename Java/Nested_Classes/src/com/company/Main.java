package com.company;

import java.awt.*;

public class Main {

    //Nested Classes
    //Classes defined within  class
    //Four types;
    //1. Static nested class
    //2. Inner class
    //3. Local inner class
    //4. Anonymous inner class
    public static void main(String[] args) {

        /*
        //Static nested class:
        Outer.Inner myObject = new Outer.Inner();
        myObject.foo();
        */



        //Inner class:
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.foo();



        //Local inner class:
        SomeClass someObject = new SomeClass();
        Cat c = new Cat();
        c.name = "Tom";
        Dog d = someObject.someFunction(c);
        d.bark();


        //Anonymous inner class:
        SomeOtherClass someOtherObject = new SomeOtherClass();
        d = someOtherObject.someFunction();
        d.bark();


    }
}

/*
//1.Static nested class:
class Outer {
    static class Inner {
        public void foo(){
            System.out.println("foo");
        }
    }
}
*/


//2. Inner class:
class Outer {
    class Inner {
        public void foo(){
            System.out.println("foo");
        }
    }
}


//3. Local inner class
class Cat{
    String name;

    @Override
    public String toString() {
        return super.toString();
    }
}
class Dog {
    void bark(){
        System.out.println("waf waf");
    }
}
class SomeClass{
    Dog someFunction(Cat cat){
        class Poodle extends Dog{
            @Override
            void bark() {
                System.out.println("bff.. bfff.." + cat.name);
            }

            void goToSleep(){
                System.out.println("going to sleep");
            }
        }
        Poodle p = new Poodle();
        p.goToSleep();
        return p;
    }
}


//4. Anonymous inner class
class SomeOtherClass{
    Dog someFunction(){
        return new Dog(){
            @Override
            void bark() {
                System.out.println("bff.. Bfff..");
            }
        };
    }
}

package com.company.mathfunctions;

/**
 * Created on 28/02/2016.
 */

public class Car {
    int color;
    String make,model;
    int year;
    String licensePlate;

    public void drive(){
        System.out.println("driving...");
    }

    public void stop(){
        System.out.println("stopping");
    }

    public void honk(){
        System.out.println("paa paa");
    }
}

class Boat{

}
//each file may contain no more than one public class
//class that marked "public" must be named same name as the filename
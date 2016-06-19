package com.company;

public class Main {

    public static void main(String[] args) {

        CarSales carSales = new CarSales(5);
        Owner ownerAlex = new Owner("Alex","03-4853748");
        Owner ownerDavid = new Owner("David","03-4854548");
        Owner ownerMoshe = new Owner("Moshe","03-4833748");


        carSales.addCar(new Car(ownerAlex, "Toyota", true, false,2012, 5, 25000, 45000, 50000, "54-637-65"));
        carSales.addCar(new Car(ownerDavid, "Mazda", true, false,2010, 5, 10000, 45000, 50000, "34-647-77"));
        carSales.addCar(new Car(ownerMoshe, "Fiat", true, false,2015, 5, 15000, 45000, 50000, "78-866-64"));
        carSales.addCar(new Car(ownerAlex, "Honda", true, false,2014, 5, 15000, 45000, 50000, "46-775-55"));
        carSales.addCar(new Car(ownerDavid, "Honda", true, false,2010, 5, 15000, 45000, 50000, "46-456-55"));

        //System.out.println(carSales.getNewest());


        /*System.out.println(carSales.allAttractive());

        carSales.makeBidsOnCheapNotOverUsed("02-2645436",50000);
        System.out.println(carSales);
        */

        //System.out.println(carSales.ownersOfType("Honda"));

        carSales.removeCar("46-775-55");
        carSales.removeCar("54-637-65");
        carSales.removeCar("46-456-55");

        System.out.println(carSales);

    }
}

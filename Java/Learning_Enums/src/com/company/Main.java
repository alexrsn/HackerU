package com.company;

import java.time.Period;

public class Main {

    public static void main(String[] args) {
        City myCity = City.KFAR_SABA;
        Person p = new Person("John","Doe",Gender.MALE);
        PrinterType printerType = PrinterType.DOTMATRIX;
        System.out.println(printerType.getCode());

        Dog d = Dog.getDog("yombo");
    }


    public static void checkCity(City city){
        if(city == City.PETAH_TIKVA){
            System.out.println("Petah Tikva");
        }

        switch(city){
            case KFAR_SABA:
                System.out.println("kfar saba");
                break;
            case TEL_AVIV:
                System.out.println("tel aviv");
                break;
        }
    }

}

enum City{
    KFAR_SABA("Kfar Saba"), TEL_AVIV("Tel Aviv"), RAMAT_GAN("Ramat Gan"), RAANANA("Ra'anana"),
    PETAH_TIKVA("Petah Tikva"),RISHON_LETSION("Rishon Letsion");

    private String cityName;

    private City(String name){
        this.cityName= name;
    }

    public String getCityName(){
        return cityName;
    }

}

enum Gender{
    MALE,FEMALE,OTHER
}

class Person{
    private String firstName,lastName;
    private Gender gender;

    public Person(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

enum PrinterType{
    DOTMATRIX(1), INKJET(2), LASER(3), THREE_DIMENSIONS(4), THERMAL(5);

    private int code;

    private PrinterType(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}

class Dog{
    private String name;

    private Dog(String name){
        this.name = name;
    }

    public static Dog getDog(String name){
        return new Dog(name);
    }
}
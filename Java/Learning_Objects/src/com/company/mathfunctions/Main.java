package com.company.mathfunctions;

public class Main {

    public static void main(String[] args) {
        Car c;  //reference
        c = new Car();
        //1. find a free spot for the object that is large enough to contain the object.
        //2. mark that spot as "taken".
        //3. return the address in memory that was taken(where the object exist).

        c.color = 123;
        c.licensePlate = "23256547";
        c.make = "Toyota";
        c.model = "Corolla";
        c.year = 2016;
        System.out.println(c.year);
        c.honk();
        Car c2 = new Car();
        c2.color = 456;
        c2.licensePlate = "4574743";
        c2.make = "Mazda";
        c.model = "3";
        c2.year = 2014;
        c2.drive();
        Car cBackup = c2;
        System.out.println(c2);
        c2 = c;
        c2.year++;
        System.out.println(c);
        System.out.println(c.year);


        DivisionResult divisionResult = quotient(7, 3);
        System.out.println("3 fits in 7 " + divisionResult.whole +
                " times with a remainder of " + divisionResult.remainder);

    }

    public static DivisionResult quotient(int x, int y) {
        DivisionResult divisionResult = new DivisionResult();
        if (y == 0) {
            divisionResult.whole = -1;
            return divisionResult;
        }
        int result = 0;
        int sum = y;
        int largestProductOfY = 0;
        while (sum <= x) {
            sum += y;
            largestProductOfY += y;
            result++;
        }
        divisionResult.whole = result;
        divisionResult.remainder = x - largestProductOfY;
        return divisionResult;
    }
}

class DivisionResult {
    int whole;
    int remainder;
}

class Color {
    byte red, green, blue, alpha;
}


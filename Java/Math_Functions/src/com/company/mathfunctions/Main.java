package com.company.mathfunctions;


public class Main {

    public static void main(String[] args) {
        int x = 9;
//        int y;
//        //y needs to be 3 if x is less than 5, and 4 otherwise
//        if(x<5)
//            y = 3;
//        else
//            y = 4;
//
        int y = x < 5 ? 3 : 4;

        //System.out.println(distance(7,4));
        //System.out.println(product(2,3));
        //System.out.println(quotient(7,3));
        //System.out.println(remainder(5,3));
        //System.out.println(power(4,3));
        //System.out.println(sqrt(9));
        //System.out.println(sqrt2(10));
        //System.out.println(sumOfDigits(2645));
        //System.out.println(largestDigit(2645));
        //System.out.println(reverseDigits(1293594));
        //drawRectangle(5,4);
        //drawRectangle(7,3,5,3);
        //drawRectangle2(7,3,5,3);
        //printMatrix(10,10);
        //drawX(5);
        //drawCircle(5);
        drawCircle2(5);
    }

    //in the following exercises, you may not use any mathematical
    //operator, other than +. Furthermore, you may assume that the
    //parameters are non-negative integers.
    //write the following functions:
    //1. distance, will return the distance between two integers
    //i.e: distance(5,8) returns 3.
    //2. write a function that will return the product of two integers
    //i.e product(5,8) return 40.
    //3. write a function that takes two parameters, x and y,
    // and should return how many whole times y fits in x.
    //i.e quotient(7,3) returns 2, quotient(3,7) returns 0.
    //4. write a function that takes two parameters, x and y,
    // and should return the remainder of x divided by y.
    // i.e remainder(7,3) returns 1, remainder(3,7) returns 3.
    // don't care to much about performance in this exercise.


    //in the next following exercises, you may use any mathematical
    //operator.

    //5. write a function that will take two integer parameters,
    // x and y, and will return x raised to the power of y
    // i.e power(3,3) returns 27, power(5,0) returns 1,
    // power(0,0) return -1.
    // write exercise 5 again, this time, you may not use for or while
    // meaning, you may not use loops, you may not call other functions
    //6. write a function that will take one integer parameter,
    //   and will return the squared root of that number.
    //i.e sqrt(9) returns 3, sqrt(10) returns 4.
    //7. write a function that will take one integer parameters,
    //   and will return the sum of its digits.
    //i.e sumOfDigits(123) returns 6
    //8. ....... and will return the largest digit of a number.
    //i.e largestDigit(123) returns 3
    //9. ...... and will return the number in
    // reverse order of the digits.
    //i.e reverseDigits(123) returns 321
    //i.e reverseDigits(120) returns 21
    //10. write a function that will draw a rectangle on the screen
    //    using * (print " ") to space between *.
    //11. do exercise 10 again, this time, there are two more parameters
    //    x and y, x will move the rectangle right, and y,
    //    will move the rectangle down.
    //12. draw a circle (for Shalom), parameters: radius


    public static int distance(int x, int y) {
        int small = x;
        int large = y;
        if (x > y) {
            small = y;
            large = x;
        }
        int result = 0;
        while (small + result < large)
            result++;

        return result;
    }
    //error types:
    //1. syntax error
    //2. runtime exception
    //3. logical error, the function doesn't achieve what it is supposed to achieve
    //4. logical error, the function doesn't achieve what it is supposed to achieve
    //   but only for some of the parameters.
    //5. performance issue.


    public static int product(int x, int y) {
        int small = x;
        int large = y;
        if (x > y) {
            small = y;
            large = x;
        }
        int result = 0;
        for (int i = 0; i < small; i++) {
            result += large;
        }
        return result;
    }


    public static int quotient(int x, int y) {
        if (y == 0) {
            return -1;
        }
        int result = 0;
        int sum = y;
        while (sum <= x) {
            sum += y;
            result++;
        }
        return result;
    }

    //                              7      3
    public static int remainder(int x, int y) {
        if (y == 0) {
            return -1;
        }
        return distance(x, product(quotient(x, y), y));
    }

    //distance(7,product(quotient(7, 3), 3));
    //distance(7,product(2, 3));
    //distance(7, 6);
    //1;

    /*
    public static int power(int x, int y){
        if(x==0){
            if(y==0)
                return -1;
            return 0;
        }
        if(y==0 || x==1)
            return 1;
        int result = x;
        for (int i = 1; i < y; i++) {
            result *= x;
        }
        return result;
    }
    */
    public static int power(int x, int y) {
        if (x == 0) {
            if (y == 0)
                return -1;
            return 0;
        }
        if (y == 0 || x == 1)
            return 1;

        if (y == 1)
            return x;
        return x * power(x, y - 1);
    }

    //power(7,4)
    //power(7,3)*7
    //power(7,2)*7*7
    //power(7,1)*7*7*7
    //7*7*7*7

    public static int sqrt(int x) {
        int result = 0;
        while ((result * result) < x) {
            result++;
        }
        return result;
    }

    /*public static double sqrt2(int x){
        double result = 0;
        while((result*result) < x){
            if(result*result+1<x){
                result++;
            }
            else {
                result += 0.00001;
            }
        }
        result /= 0.00001;
        int temp = (int) result;
        result =(double) temp;
        result /= 100000;
        return  result;
    }*/

    public static double sqrt2(double x) {
        double result = 0;
        double interval = 1.0;
        double nextResult;

        while ((result * result) < x) {
            nextResult = result + interval;
            if (nextResult * nextResult > x) {
                interval /= 10;
                if (interval < 0.000001)
                    break;
            }
            result += interval;
        }
        return result;
    }

    public static int sumOfDigits(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public static int largestDigit(int x) {
        int max = 0;
        while (x != 0) {
            if ((x % 10) > max) {
                max = x % 10;
            }
            x /= 10;
        }
        return max;
    }

    public static int reverseDigits(int x) {
        int num = 0;
        while (x != 0) {
            num *= 10;
            num += x % 10;
            x /= 10;
        }
        return num;
    }


    public static void drawRectangle(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void drawRectangle(int width, int height, int x, int y) {
        for (int i = 0; i < height + y; i++) {
            for (int j = 0; j < width + x; j++) {
                if (i == y && j > x || j == x && i >= y || i == height + y - 1 && j > x || j == width + x - 1 && i >= y) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void drawRectangle2(int width, int height, int x, int y) {
        for (int i = 0; i < y; i++) {
            System.out.println();
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void printMatrix(int x,int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print("("+i+","+j+")");
            }
            System.out.println();
        }
    }

    public static void drawX(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) {
                    System.out.print("* ");
                    //System.out.print("("+i+","+j+")");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static boolean isPointOnCircle(int x, int y, int radius){
        int deltaX = x - radius;
        int deltaY = y - radius;
        int distance = deltaX * deltaX + deltaY * deltaY - radius * radius;
        if(distance < 0)
            distance *= -1;
        return distance < radius;
    }

    public static void drawCircle(int radius) {
        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                System.out.print((i * i + j * j) < radius * radius ? "* " : "  ");
            }
            System.out.println();
        }
    }

    public static void drawCircle2(int radius) {
        for (int i = 0; i < radius*2+1; i++) {
            for (int j = 0; j < radius*2+1; j++) {
                System.out.print(isPointOnCircle(i, j, radius) ? "* " : "  ");
            }
            System.out.println();
        }
    }
}

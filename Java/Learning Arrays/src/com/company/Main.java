package com.company;

public class Main {

    //1. write a function that takes an array of integers as a parameter,
    //   and returns the largest number.
    //   largestNumber(int[] nums){.....}
    //2. ..... and reverses the order of the numbers in the array.
    //3. does array A contain array B ?
    //    {2,67,2,4,8,5,2,9,77}    {2,4,8}
    //4. write a function that takes an array of integers as a parameter,
    //   and sorts the numbers in an ascending order.

    public static void main(String[] args) {


        int[] arr1 = {4, -8, 100, 30, 58, 45, -78, 200, 30, -8};
        int[] arr2 = {100, 30};
        int[][] arr3 = {{6, 14}, {5, 89, 10}, {}};

        int[] arr4 = new int[4];
        arr4[0] = 6;
        arr4[1] = 7;
        arr4[2] = 3;

        int[][] arr5 = new int[3][5];
        int[][] arr6 = new int[3][];
        arr6[0] = new int[5];
        arr6[1] = new int[7];
        arr6[2] = new int[8];

        boolean[][] canvas = new boolean[50][80];
        clearCanvas(canvas);
        drawRectangle(10, 5, 30, 10, canvas);
        drawRectangle(13, 8, 30, 10, canvas);
        printCanvas(canvas);

        //arr1[0] = -78;
        //arr1[1]++;*/
        /*int x=2;
        int y=3;
        x+=y;
        y=x-y;
        x=x-y;
        System.out.println(x);
        System.out.println(y);*/


        //System.out.println(sumOfNumbers(arr1));
        /*int[] numbers = {};
        try {
            int max=largestNumber(numbers);
            System.out.println("The largest number is: " + max);
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        System.out.println("End of program");8?
        //reverseOrder(arr1);
        //printArray(arr1);
        /*if(containArray(arr1, arr2))
            System.out.println("First array contains second array!");
        else
            System.out.println("First array doesn't contain second array!");*/
        //System.out.println("Second array appears on index: " + containArray(arr1, arr2));
        /*System.out.println("Original array:");
        printArray(arr1);
        //sortArray(arr1);
        bubbleSortArray(arr1);
        System.out.println("Sorted array:");
        printArray(arr1);*/
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static int sumOfNumbers(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int largestNumber(int[] arr) throws Exception {
        if (arr == null) {
            throw new Exception("null pointer exceptions");
        }
        if (arr.length == 0) {
            throw new Exception("empty set");
        }
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    public static void reverseOrder(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    public static int containArray(int[] arr1, int[] arr2) {
        for (int i = 0; i <= arr1.length - arr2.length; i++) {
            if (arr1[i] == arr2[0]) {
                for (int j = 1; j < arr2.length; j++) {
                    if (arr1[i + j] != arr2[j])
                        break;
                    else if (j == arr2.length - 1 && arr1[i + j] == arr2[j])
                        return i;
                }
            }
        }
        return -1;
    }

    public static void sortArray(int[] arr) { //selection sort
        int temp;
        int minNumber;
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minNumber = arr[i];
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < minNumber) {
                    minNumber = arr[j];
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = minNumber;
            arr[minIndex] = temp;
        }
    }

    public static void bubbleSortArray(int[] arr) {
        boolean isSorted = false;
        int compareUpTo = arr.length - 1;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < compareUpTo; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
            compareUpTo--;
        }
    }

    public static void clearCanvas(boolean[][] canvas) {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                canvas[i][j] = false;
            }
        }
    }

    public static void drawRectangle(int x, int y, int width, int height, boolean[][] canvas) {
        for (int i = x; i < x + width; i++) {
            canvas[y][i] = true;
            canvas[y + height][i] = true;
        }
        for (int i = y; i <= y + height; i++) {
            canvas[i][x] = true;
            canvas[i][x + width] = true;
        }
    }

    public static void printCanvas(boolean[][] canvas) {

        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                System.out.print(canvas[i][j] ? "*" : " ");
            }
            System.out.println();
        }
    }
}


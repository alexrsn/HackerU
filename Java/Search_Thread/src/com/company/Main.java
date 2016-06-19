package com.company;

public class Main {

    public static void main(String[] args) {
        String[] arr = new String[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]= String.valueOf(i);
        }
        arr[49990] = "50010";
        search(arr,"50010");
    }

    public static void search(String[] strArr, String str) {
        MultiThreadedArraySearch.SearchListener searchListener =
                new MultiThreadedArraySearch.SearchListener() {
                    @Override
                    public void found(int index) {
                        System.out.println("Element found at " + index);
                    }
                };
        MultiThreadedArraySearch<String> multiThreadedArraySearch =
                new MultiThreadedArraySearch<>(strArr,searchListener,str);
    }
}

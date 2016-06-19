package com.company;

public class Main {

    public static void main(String[] args) {
        String s = "how are you today";
        String[] words = s.split("are");
        for (int i = 0; i < words.length; i++) {
          //  System.out.println(words[i]);
        }
        //System.out.println(s.concat("!!!"));

        //char a = 'Z';
        //System.out.println(a > 'a' && a < 'z');

        //System.out.println(s.substring(4, 7));

        //s.contains("saf");

        //System.out.println(s.endsWith("today"));

        //System.out.println(s);

        MyString myString = new MyString(new char[]{'h', 'e', 'l', 'l', 'o'});
        MyString myString1 = new MyString(new char[]{'a', 'b', 'c', 'd'});
        MyString myString2 = new MyString(new char[]{'l', 'l', 'c'});
        MyString myString3 = new MyString(new char[]{'a', 'b', ' ', 'c', 'b', ' ', 'e', 'b',' ','e', 'f','v'});
        MyString myString4 = new MyString(new char[]{'b',' '});
        MyString myString5 = new MyString(new char[]{});
        String str = "the boy eats an egg";
        MyString myString6 = new MyString(str.toCharArray());


        //equals, charAt, concat, contains, endsWith, indexOf(MyString), split, substring(begin,end), toUpper(use ascii code)


        //System.out.println(myString.equals(myString));
        //System.out.println(myString.charAt(5));
        //System.out.println(myString.concat(myString1));
        //System.out.println(myString.contains(myString2));
        //System.out.println(myString.endsWith(myString2));
        //System.out.println(myString.indexOf(myString2));
        MyString[] myStrings = myString6.split(' ');
        //MyString[] myStrings = myString3.split(myString4);
        for (int i = 0; i < myStrings.length; i++) {
            System.out.println(myStrings[i]);
        }
        //System.out.println(myString.substring(1,4));
        //System.out.println(myString.toUpper());

    }
}


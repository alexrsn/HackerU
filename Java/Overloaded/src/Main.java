public class Main {

    public static void main(String[] args) {
        /*byte b =9;
        aMethod(b); //short
        aMethod(9);//int
        aMethod("3");//String
        //aMethod(9.2);//Object

        //Integer x = new Integer(8);
        Integer x = 8;
        aMethod(x);//Object
        */

        //aMethod(9);


        //aMethod(10,9);
        aMethod(10L,9);
        aMethod(10,9L);

    }


    public static void aMethod(long val1,int val2){System.out.println("long,int");}
    public static void aMethod(int val1,long val2){System.out.println("int,long");}


    /*
    public static void aMethod(byte val){System.out.println("byte");}
    public static void aMethod(short val){System.out.println("short");}
    */
    /*
    public static void aMethod(int val){System.out.println("int");}
    public static void aMethod(short val){System.out.println("short");}
    public static void aMethod(Object val){System.out.println("Object");}
    public static void aMethod(String val){System.out.println("String");}
    */
}

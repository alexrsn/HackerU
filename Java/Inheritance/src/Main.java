public class Main {

    public static void main(String[] args) {
        /*
        Number[] myNumber = new Number[4];
        myNumber[0] = new Double(8.6);
        myNumber[1] = new Integer(3);
        myNumber[2] = new Float(3.2f);
        myNumber[3] = new RationalNumber(1,5);
        System.out.println(sumOfNumbers(myNumber));
        */
        Animal a = new Dog();

        Dog d = (Dog) a;
        d.bark();
        //((Dog)a).bark();

        if (a instanceof Cat) { //instanceof checks type of class
            Cat myCat = (Cat) a;
            myCat.growl();
        }

        //the list of methods that you may invoke, is determined by the type of the pointer
        a.eat("bonzo");
        //but, when the method is invoked, the method that is actually invoked is
        //determined by the type of the actual object. not by the pointer.


        Citizen john = new Citizen("John", "Smith");
        Citizen adam = new Citizen("Adam", "Price");
        System.out.println(john);
        System.out.println(adam);
    }

    public static double sumOfNumbers(Number[] nums) {
        double sum = 0.0;
        /*for (int i = 0; i < nums.length; i++) {
            Number num = nums[i];
            sum += num.doubleValue();
        }*/

        //shortcut to create foreach is iter
        for (Number num : nums) {
            sum += num.doubleValue();
        }
        return sum;
    }
}

class RationalNumber extends Number {

    private int numerator; //mone
    private int denominator; //mehane

    RationalNumber(int numerator, int denominator) {
        this.numerator = numerator;
        if (denominator == 0) {
            this.denominator = 1;
            this.numerator = Integer.MAX_VALUE;
        } else
            this.denominator = denominator;

    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        double num = this.numerator;
        double deno = this.denominator;
        return num / deno;
    }
}

class Animal {
    void goToSleep() {
        System.out.println("going to sleep");
    }

    void eat(String food) {
        System.out.println("eating " + food);
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("waf waf...");
    }

    @Override
    void eat(String food) {
        System.out.println("yam yam ");
        super.eat(food);
    }
}

class Cat extends Animal {
    void growl() {
        System.out.println("growl");
    }
}

class Citizen {
    private String firstName, lastName;
    private final int nationalId; //final can be assighned in this line or in constructor only
    private static int counter = 0; //static variable means it's global for the class


    //static constructor used to assign values to static variables runs only once on first initialization of the class
    static {
        int sum = 0;
        for (int i = 0; i < 50; i++) {
            sum += i;
        }
        counter = sum;
    }


    public Citizen(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = ++counter;
    }

    @Override
    public String toString() {
        return "Citizen-first name: " + firstName + " last name: " + lastName + " id: " + nationalId;
    }

}
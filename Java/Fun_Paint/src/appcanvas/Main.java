package appcanvas;

import graphicshapes.Circle;
import graphicshapes.Shape;

public class Main {

    public static void main(String[] args) {
        //creating a reference/pointer:
        Circle myCircle;
        //create an object of type Circle, and making out reference point to the newly created object.
        myCircle = new Circle();
        //creating an object is called: instantiation from the word "instance"
        //it is also called, initialization

        //System.out.println(myCircle.toString());
        //System.out.println(myCircle);
        //double a =myCircle.area();

        /*
        byte red = 8;
        byte green = 20;
        byte blue = -40;
        myCircle.fillColor(red, green, blue);
        myCircle.fillColor(0.8f, 12.3f, -50.14f);

        Rectangle myRectangle = new Rectangle(34, 12);
        myRectangle.setWidth(15);

        //Shape s = new Shape(); //cannot be initialized because Shape is abstract class
        Shape[] someShapes = {myCircle, myRectangle};
        System.out.println(sumOfArea(someShapes));

        Circle c = new Circle(10);
        c.costPerUnitOfArea = 2;
        System.out.println(c.totalCost());

        Circle c2 = (Circle) getShape();

        Point p1 = new Point(2, 4);
        System.out.println(p1);

        Point3D p2 = new Point3D(2, 4, 6);
        System.out.println(p2);

        Point4D p3 = new Point4D(2, 4, 5, 6);
        System.out.println(p3);
        */

        /*
        Point p1 = new Point(15, 4);
        Point p2 = new Point(15, 4);

        boolean isEqual = p1.equals(p2);
        System.out.println(isEqual);
        */


        //create object from abstract class
        Shape s = new Shape() {
            @Override
            public double area() {
                return 0;
            }
        };


        byte b = 127;
        int x = b; //implicit cast
        byte b2 = (byte)x; //explicit cast
        //Shape.Color whiteColor = new Shape.Color((byte)127,(byte)127,(byte)127,(byte)127);
        Shape.Color whiteColor = new Shape.Color(b,b,b,b);
        s.setColor(whiteColor);



    }

    public static double sumOfArea(Shape[] shapes) {
        double sum = 0.0;
        for (Shape s : shapes) {
            sum += s.area();
        }
        return sum;
    }

    public static Shape getShape() {
        Circle c = new Circle();
        return c;
    }

}

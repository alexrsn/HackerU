package graphicshapes;

import  static java.lang.Math.PI;
/**
 * Created on 02/03/2016.
 */
public class Circle extends Shape {

    //private int x;
    //int y;
    private Point center;
    private int _radius; //private variables names start with _

    private final static int DEFAULT_X = 20;
    private final static int DEFAULT_Y = 20;

    //access modifiers: Public Private Protected Default
    //Public - can be accessed from all places
    //Private - can be accessed only in same Class
    //Protected - can be accessed only from same package or extended Class
    //Default - can be accessed only from same package

    public Circle(int x, int y, int radius) {
        //this.x = x;
        //this.y = y;
        this.center = this.new Point(x,y);
        if (radius >= 0)
            this._radius = radius;
    }

    public Circle(int x,int y){
        this(x,y,10);
    }

    public Circle(int radius){
        this(10,10,radius);
    }

    public Circle() {   //this is default constructor
        this(DEFAULT_X,DEFAULT_Y);//Constructor overload (must be on first line of constructor)
    }

    public double area() {
        //Math.PI is a constant, it is a final static field of the class Main
        //(static means it belongs to the class as appose to non-static which belongs to
        //an object created from the class)
        return PI * _radius * _radius;
    }

    void fillColor() {
        System.out.println("color: " + color);
    }

    public void fillColor(byte red, byte green, byte blue) {
        //System.out.println("color: " + color);
        System.out.println("fillColor 1");
    }

    public void fillColor(float hue, float saturation, float brightness) {
        //System.out.println("color: " + color);
        System.out.println("fillColor 2");
    }

    @Override
    public String toString() {
        //return "center = (" + x + "," + y + ") and radius = " + radius;
        return "center = (" + center + ") and radius = " + _radius;
    }


    public class Point {


        private int xPos, yPos;

        public Point(int x, int y) {
            setXPos(x);
            setYPos(y);
        }

        public Point(Point p) {
            this(p.xPos, p.yPos);
        }

        public int getXPos() {
            return xPos;
        }

        public void setXPos(int xPos) {
            if (xPos >= 0)
                this.xPos = xPos;
        }

        public int getYPos() {
            return yPos;
        }

        public void setYPos(int yPos) {
            if (yPos >= 0)
                this.yPos = yPos;
        }

        @Override
        public String toString() {
            return "(" + commaSeparatedComponents() + ")";
        }

        public String commaSeparatedComponents() {
            return xPos + "," + yPos;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null)
                return false;
            if(this == obj)
                return true;
            if(obj instanceof Point){
                Point other = (Point)obj;
                return other.xPos == this.xPos && other.yPos == this.yPos;
            }
            return false;
        }
    }
}

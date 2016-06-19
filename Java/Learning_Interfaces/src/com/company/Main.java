package com.company;

public class Main {

    public static void main(String[] args) {
        SomeInterface someInterface = new SomeClass();
        someInterface.stam();
        //someInterface.stam2(); //error: stam2 not defined in interface

        MotionDetector motionDetector = new MotionDetector();
        AlarmSpeaker alarmSpeaker = new AlarmSpeaker();
        motionDetector.setListener(alarmSpeaker);
        motionDetector.motion();

        Triangle t = new Triangle();
        //t.area();  // TODO: check - throws null pointer exception

        Drawable[] drawables = new Drawable[4];
        drawables[0] = new Point(3, 5);
        drawables[1] = new Segment(new Point(3,1), new Point(7,8));
        drawables[2] = new Triangle();
        drawables[3] = new Image();

        boolean[][] canvas = new boolean[50][50];
        for(Drawable d : drawables)
            d.drawOnCanvas(canvas);
    }

    public static void sortArray(Comparable[] arr) {
        boolean isSorted = false;
        int compareUpTo = arr.length - 1;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < compareUpTo; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    Comparable temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
            compareUpTo--;
        }
    }
}

interface Drawable{
    public void drawOnCanvas(boolean[][] canvas);
}


class Image implements Drawable{

    @Override
    public void drawOnCanvas(boolean[][] canvas) {
        // TODO: draw image on canvas
    }
}

class Point implements Comparable,Drawable {

    private int xPos, yPos;

    public Point(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Point(Point other) {
        this(other.xPos, other.yPos);
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * distance from another point
     *
     * @param p another point to calculate distance from
     * @return the distance from the other point or -1 if p is null
     */
    public double distanceFromPoint(Point p) {
        if (p == null) {
            return -1;
        }
        double deltaX = this.xPos - p.xPos;
        double deltaY = this.yPos - p.yPos;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public double distanceFromOrigin() {
        return distanceFromPoint(new Point(0, 0));
    }

    @Override
    public void drawOnCanvas(boolean[][] canvas){
        //// TODO: code to draw point on canvas
    }
}

class Segment implements Drawable{
    private Point p1, p2;
    private double length;
    private boolean isLengthGood;

    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        isLengthGood = false;
    }

    public Segment(Segment seg) {
        this(seg.getP1(), seg.getP2());
    }

    public Point getP1() {
        return new Point(p1);
    }

    public void setP1(Point p1) {
        this.p1 = p1;
        isLengthGood = false;
    }

    public Point getP2() {
        return new Point(p2);
    }

    public void setP2(Point p2) {
        this.p2 = p2;
        isLengthGood = false;
    }

    public double length() {
        if (!isLengthGood) {
            length = p1.distanceFromPoint(p2);
            isLengthGood = true;
        }
        return length;
    }

    public double distanceFromSegment(Point p) {
        //TODO: calculate distance from segment
        return 0;
    }

    public boolean isPointOnSegment(Point p) {
        //TODO: take care of vertical or horizontal segment
        if (distanceFromSegment(p) == 0) {
            int left = p1.getXPos();
            int right = p2.getXPos();
            if (p1.getXPos() > p2.getXPos()) {
                left = p2.getXPos();
                right = p1.getXPos();
            }
            if (p.getXPos() >= p1.getXPos() && p.getXPos() <= p2.getXPos()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawOnCanvas(boolean[][] canvas) {
        //TODO: code to draw segment on canvas
    }
}

class Triangle implements Drawable{
    private Point p1, p2, p3;

    @Override
    public void drawOnCanvas(boolean[][] canvas) {
        Segment seg1 = new Segment(p1, p2);
        Segment seg2 = new Segment(p2, p3);
        Segment seg3 = new Segment(p3, p1);
        seg1.drawOnCanvas(canvas);
        seg2.drawOnCanvas(canvas);
        seg3.drawOnCanvas(canvas);
    }

    public double perimeter() {
        Segment seg1 = new Segment(p1, p2);
        Segment seg2 = new Segment(p2, p3);
        Segment seg3 = new Segment(p3, p1);
        return seg1.length() + seg2.length() + seg3.length();
    }


    public double area() {
        Segment base = new Segment(p1, p2);
        return base.distanceFromSegment(p3) * base.length() * 0.5;
    }
}

interface SomeInterface {
    /**
     * stam should do stam things
     */
    void stam();

}

class SomeClass implements SomeInterface {

    public void stam2() {

    }

    @Override
    public void stam() {
        System.out.println("in stam()");
    }
}

class A implements Comparable {

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

class MotionDetector {
    interface MotionListener {
        void motionDetected();
    }

    private MotionListener listener;

    public void setListener(MotionListener listener) {
        this.listener = listener;
    }

    public void motion() {
        //code that detects motion
        if (listener != null) {
            listener.motionDetected();
        }
    }
}

class AlarmSpeaker implements MotionDetector.MotionListener {

    @Override
    public void motionDetected() {
        System.out.println("Stop!!! don't move");
    }
}


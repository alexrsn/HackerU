package graphicshapes;

import graphicshapes.Shape;

/**
 * Created on 02/03/2016.
 */

//final class cannot be extended
public final class Rectangle extends Shape {

    private int width, height;

    public Rectangle(int width, int height) {
        setHeight(height);
        setWidth(width);
    }

    public void draw() {
        System.out.println("color: " + color);
    }

    /**
     * returns the width of the rectangle
     *
     * @return returns the width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width of the rectangle
     *
     * @param width use only positive numbers
     */
    public void setWidth(int width) {
        if (width > 0)
            this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > 0)
            this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

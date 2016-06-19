package appcanvas;

/**
 * Created on 06/03/2016.
 */
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

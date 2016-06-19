package appcanvas;

/**
 * Created on 06/03/2016.
 */
public class Point4D extends Point3D {

    private int x4;

    public Point4D(int x1, int x2, int x3, int x4) {
        super(x1, x2, x3);
        setX4(x4);
    }

    public int getZPos() {
        return x4;
    }

    public void setX4(int x4) {
        if (x4 >= 0)
            this.x4 = x4;
    }

    @Override
    public String commaSeparatedComponents() {
        return super.commaSeparatedComponents() + "," + x4;
    }
}

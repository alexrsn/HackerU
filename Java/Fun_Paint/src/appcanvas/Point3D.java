package appcanvas;

/**
 * Created on 06/03/2016.
 */
public class Point3D extends Point {
    private int zPos;

    public Point3D(int xPos, int yPos, int zPos) {
        super(xPos, yPos);
        setZPos(zPos);
    }

    public int getZPos() {
        return zPos;
    }

    public void setZPos(int zPos) {
        if (zPos >= 0)
            this.zPos = zPos;
    }

    @Override
    public String commaSeparatedComponents() {
        return super.commaSeparatedComponents() + "," + zPos;
    }
}

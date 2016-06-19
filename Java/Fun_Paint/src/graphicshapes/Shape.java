package graphicshapes;

/**
 * Created on 02/03/2016.
 */

//abstract class can not be initialized
public abstract class Shape {
    protected Color color;

    public double costPerUnitOfArea;

    public abstract double area(); //must be override on extended class


    //final in method means it can't be override
    public final double totalCost(){
        return costPerUnitOfArea * area();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static class Color{
        private byte red,green,blue,alpha;

        public Color(byte red, byte green, byte blue, byte alpha) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }

        @Override
        public String toString() {
            return "Color{" +
                    "red=" + red +
                    ", green=" + green +
                    ", blue=" + blue +
                    ", alpha=" + alpha +
                    '}';
        }

        public byte getRed() {
            return red;
        }

        public void setRed(byte red) {
            this.red = red;
        }

        public byte getGreen() {
            return green;
        }

        public void setGreen(byte green) {
            this.green = green;
        }

        public byte getBlue() {
            return blue;
        }

        public void setBlue(byte blue) {
            this.blue = blue;
        }

        public byte getAlpha() {
            return alpha;
        }

        public void setAlpha(byte alpha) {
            this.alpha = alpha;
        }
    }
}


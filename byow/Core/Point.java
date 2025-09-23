package byow.Core;


import java.io.Serializable;

public class Point implements Serializable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPointAsString() {
        int coorX = getX();
        int coorY = getY();
        String string = coorX + "," + coorY;
        return string;
    }
}

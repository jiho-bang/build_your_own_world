package byow.Core;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private Point center;
    private ArrayList<String> pointList;
    public Room(Point center) {
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }








    /*public void drawRoom(int width, int height, Point start) {
        int maxH = start.getY() + height;
        int maxW = start.getX() + width;
        if(maxH > world.getHeight()) {
            maxH = world.getHeight() - 1;
        }
        if(maxW > world.getWidth()) {
            maxW = world.getWidth() - 1;
        }
        for (int i = start.getX(); i < maxH; i++) {
            world.setWall()
        }
    }

     */

}

package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Random;

public class AltWorld implements Serializable {
    private TETile[][] altWorld;
    private ArrayList<Point> allPoints;
    private ArrayList<Point> currPos;
    private ArrayList<String> visited;
    private int width;
    private int height;
    private TETile avatar;

    public AltWorld() {
        this.width = 60;
        this.height = 30;
        this.altWorld = new TETile[60][60];
        this.allPoints = new ArrayList<Point>();
        this.currPos = new ArrayList<Point>();
        this.visited = new ArrayList<String>();
        for (int x = 0; x < 60; x++) {
            for (int y = 0; y < 60; y++) {
                altWorld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public AltWorld(int width, int height, TETile avatar) {
        this.altWorld = new TETile[width][height];
        this.allPoints = new ArrayList<Point>();
        this.currPos = new ArrayList<Point>();
        this.visited = new ArrayList<String>();
        this.width = width;
        this.height = height;
        this.avatar = avatar;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                altWorld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] getAlt() {
        return altWorld;
    }

    public void startAltWorld(TETile av) {
        gameExplanation("Fill in every square in the plus to win!");
        StdDraw.pause(3000);

        TERenderer ter = new TERenderer();
        ter.initialize(60, 60);

        AltWorld alt = new AltWorld(60, 60, av);

        alt.drawPlus();
        alt.createPlayer();
        ter.renderFrame(alt.getAlt());
        TERenderer end = new TERenderer();
        boolean check = false;

        char user;
        while (!check) {
            if (StdDraw.hasNextKeyTyped()) {
                user = StdDraw.nextKeyTyped();
                check = alt.moveAvatar(user);
                ter.renderFrame(alt.getAlt());
            }
        }
        drawFrame("Win!");
        StdDraw.pause(1000);
        return;
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width / 2, this.height, s);
        StdDraw.show();
    }

    public void gameExplanation(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width - 17, this.height / 2, s);
        StdDraw.show();
    }

    public void drawPlus() {
        Random random = new Random();
        int max = 40;
        int min = 30;
        int plusHeightMax = random.nextInt(max + 1 - min) + min;
        int rand = random.nextInt(10 + 1 - 4) + 4;
        int plusWidthMax = plusHeightMax;
        int plusHeightMin = plusWidthMax - rand;
        int plusWidthMin = plusHeightMin;
        int plusHeight = plusHeightMax - plusHeightMin;

        for (int x = plusWidthMin; x < plusWidthMax; x += 1) {
            for (int y = plusHeightMin; y < plusHeightMax; y += 1) {
                Point point = new Point(x, y);
                altWorld[x][y] = Tileset.WALL;
                allPoints.add(point);
                if (x >= plusWidthMin && x < plusWidthMin + (plusHeight / 3)) {
                    if ((y >= plusHeightMin && y < plusHeightMin + (plusHeight / 3))
                            || (y < plusHeightMax && y >= plusHeightMax - (plusHeight / 3))) {
                        altWorld[x][y] = Tileset.NOTHING;
                        allPoints.remove(point);
                    }
                }
                if (x < plusWidthMax && x >= plusWidthMax - (plusHeight / 3)) {
                    if ((y < plusHeightMax && y >= plusHeightMax - (plusHeight / 3))
                            || (y >= plusHeightMin && y < plusHeightMin + (plusHeight / 3))) {
                        altWorld[x][y] = Tileset.NOTHING;
                        allPoints.remove(point);
                    }
                }
            }
        }
    }

    public void createPlayer() {
        int created = 0;
        while (created < 1) {
            Random random = new Random();
            int randomIndex = random.nextInt((allPoints.size() - 1) + 1 - 0) + 0;
            Point startPos = allPoints.get(randomIndex);
            if (altWorld[startPos.getX()][startPos.getY()] == Tileset.WALL) {
                currPos.add(startPos);
                visited.add(startPos.getPointAsString());
                altWorld[startPos.getX()][startPos.getY()] = avatar;
                created++;
            }
        }
    }

    public boolean moveAvatar(char key) {
        boolean check = true;
        switch (key) {
            case 'w' -> check = move("up");
            case 'a' -> check = move("left");
            case 's' -> check = move("down");
            case 'd' -> check = move("right");
            default -> check = true;
        }
        return check;
    }

    public boolean move(String direction) {
        Point curr = currPos.get(currPos.size() - 1);
        Point newPos;
        switch (direction) {
            case "up" -> newPos = new Point(curr.getX(), curr.getY() + 1);
            case "down" -> newPos = new Point(curr.getX(), curr.getY() - 1);
            case "right" -> newPos = new Point(curr.getX() + 1, curr.getY());
            case "left" -> newPos = new Point(curr.getX() - 1, curr.getY());
            default -> newPos = new Point(-5, -5);
        }
        if (validatePoint(newPos)) {
            if (!visited.contains(newPos.getPointAsString())) {
                visited.add(newPos.getPointAsString());
            }
            altWorld[curr.getX()][curr.getY()] = Tileset.WATER;
            altWorld[newPos.getX()][newPos.getY()] = avatar;
            curr = newPos;
            currPos.add(curr);
            if (visited.size() == allPoints.size()) {
                return true;
            }
        } else {
            System.out.println("Can't move");
        }
        return false;
    }

    public boolean validatePoint(Point point) {
        if (altWorld[point.getX()][point.getY()] == Tileset.NOTHING) {
            return false;
        }
        return true;
    }
}


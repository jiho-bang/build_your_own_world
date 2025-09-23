package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Random;

public class SecondAltWorld implements Serializable {
    private TETile[][] altWorld;
    private ArrayList<Point> allPoints;
    private ArrayList<Point> currPos;
    private int flowers;
    private int width;
    private int height;
    private TETile avatar;

    public SecondAltWorld() {
        this.width = 60;
        this.height = 30;
        this.altWorld = new TETile[60][30];
        this.allPoints = new ArrayList<Point>();
        this.currPos = new ArrayList<Point>();
        this.flowers = 0;
        for (int x = 0; x < 60; x++) {
            for (int y = 0; y < 30; y++) {
                altWorld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public SecondAltWorld(int width, int height, TETile avatar) {
        this.altWorld = new TETile[width][height];
        this.allPoints = new ArrayList<Point>();
        this.currPos = new ArrayList<Point>();
        this.flowers = 0;
        this.width = width;
        this.height = height;
        this.avatar = avatar;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                altWorld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] getSec() {
        return altWorld;
    }

    public void startSecondAlt(TETile av) {
        gameExplanation("Collect all the flowers to win!");
        StdDraw.pause(3000);

        TERenderer ter = new TERenderer();
        ter.initialize(60, 30);

        SecondAltWorld sec = new SecondAltWorld(60, 30, av);

        sec.drawBlock();
        sec.generateFlowers();
        sec.createPlayer();
        ter.renderFrame(sec.getSec());
        TERenderer end = new TERenderer();
        boolean check = false;

        char user;
        while (!check) {
            if (StdDraw.hasNextKeyTyped()) {
                user = StdDraw.nextKeyTyped();
                check = sec.moveAvatar(user);
                ter.renderFrame(sec.getSec());
            }
        }
        drawFrame("Win!");
        StdDraw.pause(1000);
        return;
    }

    public void drawBlock() {
        for (int x = 20; x < 35; x += 1) {
            for (int y = 5; y < 10; y += 1) {
                altWorld[x][y] = Tileset.WALL;
                Point point = new Point(x, y);
                allPoints.add(point);
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
                altWorld[startPos.getX()][startPos.getY()] = avatar;
                created++;
            }
        }
    }
    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width / 2, this.height / 2, s);
        StdDraw.show();
    }

    public void gameExplanation(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width - 13, this.height / 2, s);
        StdDraw.show();
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
            altWorld[curr.getX()][curr.getY()] = Tileset.WALL;
            altWorld[newPos.getX()][newPos.getY()] = avatar;
            curr = newPos;
            if (flowers == 6) {
                return true;
            }
            currPos.add(curr);
        } else {
            System.out.println("Can't move");
        }
        return false;
    }

    public boolean validatePoint(Point point) {
        if (altWorld[point.getX()][point.getY()] == Tileset.FLOWER) {
            flowers++;
        }
        if (altWorld[point.getX()][point.getY()] == Tileset.NOTHING) {
            return false;
        }
        return true;
    }

    public void generateFlowers() {
        int created = 0;
        while (created < 6) {
            Random random = new Random();
            int randomIndex = random.nextInt((allPoints.size() - 1) + 1 - 0) + 0;
            Point curr = allPoints.get(randomIndex);
            if (altWorld[curr.getX()][curr.getY()] != Tileset.FLOWER) {
                altWorld[curr.getX()][curr.getY()] = Tileset.FLOWER;
                created++;
            }
        }
    }
}

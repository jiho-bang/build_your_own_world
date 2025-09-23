package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Random;

public class ThirdAltWorld implements Serializable {
    private TETile[][] altWorld;
    private ArrayList<Point> allPoints;
    private ArrayList<Point> currPos;
    private int trees;
    private int width;
    private int height;

    public ThirdAltWorld() {
        this.width = 60;
        this.height = 30;
        this.altWorld = new TETile[60][30];
        this.allPoints = new ArrayList<Point>();
        this.currPos = new ArrayList<Point>();
        this.trees = 0;
        for (int x = 0; x < 60; x++) {
            for (int y = 0; y < 30; y++) {
                Point point = new Point(x, y);
                allPoints.add(point);
                altWorld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public ThirdAltWorld(int width, int height) {
        this.altWorld = new TETile[width][height];
        this.allPoints = new ArrayList<Point>();
        this.currPos = new ArrayList<Point>();
        this.trees = 0;
        this.width = width;
        this.height = height;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                allPoints.add(point);
                altWorld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] getThr() {
        return altWorld;
    }

    public void startThirdAlt() {
        gameExplanation("There are 10+ trees in this picture, guess correctly to win!");
        StdDraw.pause(3000);

        TERenderer ter = new TERenderer();
        ter.initialize(60, 30);

        ThirdAltWorld thr = new ThirdAltWorld(60, 30);

        int num = thr.generateTrees();
        thr.generateRandom();
        ter.renderFrame(thr.getThr());
        TERenderer end = new TERenderer();
        String s = String.valueOf(num);
        System.out.println(s);
        int count = 0;
        while (count < 1) {
            String userInput = solicitNCharsInput(2);
            if (s.equals(userInput)) {
                count++;
            } else {
                drawFrame("Lose");
                StdDraw.pause(1000);
                return;
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
        StdDraw.text(this.width / 2, this.height / 2, s);
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

    public String solicitNCharsInput(int n) {
        String content = "";
        int count = 0;
        while (count < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char user = StdDraw.nextKeyTyped();
                content = content + user;
                count++;
            }
        }
        return content;
    }

    public int generateTrees() {
        Random random = new Random();
        int num = random.nextInt(18 + 1 - 10) + 10;
        int created = 0;
        while (created < num) {
            int randomIndex = random.nextInt((allPoints.size() - 1) + 1 - 0) + 0;
            Point curr = allPoints.get(randomIndex);
            if (altWorld[curr.getX()][curr.getY()] != Tileset.TREE) {
                altWorld[curr.getX()][curr.getY()] = Tileset.TREE;
                created++;
            }
        }
        return num;
    }

    public void generateRandom() {
        Random random = new Random();
        int created = 0;
        while (created < 90) {
            int randomIndex = random.nextInt((allPoints.size() - 1) + 1 - 0) + 0;
            Point curr = allPoints.get(randomIndex);
            if (altWorld[curr.getX()][curr.getY()] != Tileset.TREE) {
                altWorld[curr.getX()][curr.getY()] = randomTile();
                created++;
            }
        }
    }
    public TETile randomTile() {
        Random random = new Random();
        int num = random.nextInt(3);
        switch (num) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.WATER;
            case 2: return Tileset.WALL;
            default: return Tileset.NOTHING;
        }
    }
}

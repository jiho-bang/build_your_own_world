package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdDraw;

import byow.TileEngine.Tileset;

import java.awt.*;
import java.io.*;

public class Engine implements Serializable {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 90;
    public static final int HEIGHT = 40;

    private World world;
    private String seed;
    private TETile[][] save;
    private World worldSave;
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File SAVEDWORLD = Utils.join(CWD, "world.txt");

    public Engine() {
        //world = new World(WIDTH, HEIGHT, "");
        seed = "";
        save = null;
        if (!SAVEDWORLD.exists()) {
            Utils.writeObject(SAVEDWORLD, new World(true));
        }
    }



    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        StdDraw.setCanvasSize(this.WIDTH * 16, this.HEIGHT * 16);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.setXscale(0, this.WIDTH);
        StdDraw.setYscale(0, this.HEIGHT);
        int place = this.HEIGHT / 4;
        StdDraw.text(this.WIDTH / 2, place, "Quit (Q)");
        StdDraw.text(this.WIDTH / 2, place * 2, "Load (L)");
        StdDraw.text(this.WIDTH / 2, place * 3, "New World (N)");
        StdDraw.show();
        int count = 1;
        char user;

        while (count > 0) {
            if (StdDraw.hasNextKeyTyped()) {
                user = StdDraw.nextKeyTyped();
                if (user == 'n') {
                    //World wor = new World("");
                    StdDraw.clear(Color.BLACK);
                    StdDraw.setPenColor(Color.WHITE);
                    StdDraw.setFont(fontBig);
                    StdDraw.setXscale(0, this.WIDTH);
                    StdDraw.setYscale(0, this.HEIGHT);
                    StdDraw.text(this.WIDTH / 2, place * 3, "Enter seed:");
                    String inputSeed = "";
                    while (count > 0) {
                        if (StdDraw.hasNextKeyTyped()) {
                            char in = StdDraw.nextKeyTyped();
                            if (in == 's') {
                                break;
                            }
                            inputSeed = inputSeed + in;
                            StdDraw.clear(Color.BLACK);
                            StdDraw.setPenColor(Color.WHITE);
                            StdDraw.setFont(fontBig);
                            StdDraw.setXscale(0, this.WIDTH);
                            StdDraw.setYscale(0, this.HEIGHT);
                            StdDraw.text(this.WIDTH / 2, place * 3, "Enter seed:");
                            StdDraw.text(this.WIDTH / 2, place * 2, inputSeed);
                            StdDraw.show();
                        }

                    }
                    World wor = new World(inputSeed);
                    wor.startGame();

                } else if (user == 'q' || user == 'Q') {
                    System.exit(0);
                } else if (user == 'l' || user == 'L') {
                    if (Utils.readObject(SAVEDWORLD, World.class).isEmpty()) {
                        System.exit(0);
                    }
                }
            }
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        //TERenderer ter = new TERenderer();
        //ter.initialize(90 , 40);
        String copy = input;
        String numb;
        if (input.isEmpty()) {
            numb = "";
        } else {
            numb = copy.replaceAll("[^0-9]", "");
        }
        if (input.length() >= 1 && input.charAt(0) == 'l') {
            //World wor = new World(save, numb);
            World wor = Utils.readObject(SAVEDWORLD, World.class);

            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == ':' && !(i + 1 >= input.length())
                        && input.charAt(i + 1) == 'q') {
                    Utils.writeObject(SAVEDWORLD, wor);
                    //ter.renderFrame(wor.getWorld());
                    return wor.getWorld();
                } else if (input.charAt(i) == 'w' || input.charAt(i) == 's'
                        || input.charAt(i) == 'a' || input.charAt(i) == 'd') {
                    wor.moveAvatar(input.charAt(i));
                }
            }
            //ter.renderFrame(wor.getWorld());
            return wor.getWorld();
        } else {
            World wor = new World(90, 40, numb, Tileset.AVATARTWO);

            wor.generateRooms();
            wor.connect();
            wor.createPlayer();
            //Main.startGame(numb, wor);
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == ':' && !(i + 1 >= input.length())
                        && input.charAt(i + 1) == 'q') {
                    save = wor.getWorld();
                    worldSave = wor;
                    //ter.renderFrame(wor.getWorld());
                    Utils.writeObject(SAVEDWORLD, wor);
                    return wor.getWorld();
                } else if (input.charAt(i) == 'w' || input.charAt(i) == 's'
                        || input.charAt(i) == 'a' || input.charAt(i) == 'd') {
                    wor.moveAvatar(input.charAt(i));
                }
            }

            TETile[][] finalWorldFrame = wor.getWorld();
            save = finalWorldFrame;
            worldSave = wor;
            //ter.renderFrame(wor.getWorld());
            return finalWorldFrame;
        }
    }

    public String getSeed() {
        return this.seed;
    }
}

package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.TileEngine.TERenderer;
import edu.princeton.cs.algs4.StdDraw;

/** This is the main entry point for the program. This class simply parses
 *  the command line inputs, and lets the byow.Core.Engine class take over
 *  in either keyboard or input string mode.
 */
public class Main {
    private World world;
    private static Room room;

    public static void main(String[] args) {
        //NOTE: uncomment one of the following three to see the current world or other encounters.

        //World w = new World("");
        //w.startGame();
        //Engine r = new Engine();
        //TETile[][] check = r.interactWithInputString("n9127564470038628925sdaddawwawas:q");
        //check = r.interactWithInputString("lwasaasswadada:q");
        //check = r.interactWithInputString("ladds");
        //TETile[][] check2 = r.interactWithInputString("n9127564470038628925sdaddawwawaswasaasswadadaadds");
        //System.out.println(check.equals(check2));

        //r.interactWithKeyboard();

        //AltWorld a = new AltWorld();
        //a.startAltWorld();

        //SecondAltWorld s = new SecondAltWorld();
        //s.startSecondAlt();

        //ThirdAltWorld t = new ThirdAltWorld();
        //t.startThirdAlt();

        //startGame();
        /*
        TERenderer ter = new TERenderer();
        ter.initialize(90 , 40);

        World world = new World(90, 40, "");

        world.generateRooms();
        world.connect();
        ter.renderFrame(world.getWorld());

         */

        if (args.length > 2) {
            System.out.println("Can only have two arguments - the flag and input string");
            System.exit(0);
        } else if (args.length == 2 && args[0].equals("-s")) {
            Engine engine = new Engine();
            engine.interactWithInputString(args[1]);
            System.out.println(engine.toString());
        } else if (args.length == 2 && args[0].equals("-p")) {
            // DO NOT CHANGE THESE LINES YET ;)
            System.out.println("Coming soon.");
        } else {
            // DO NOT CHANGE THESE LINES YET ;)
            Engine engine = new Engine();
            engine.interactWithKeyboard();
        }




    }

    public static void startGame() {
        TERenderer ter = new TERenderer();
        ter.initialize(90 , 40);

        World world = new World(90, 40, "", Tileset.AVATARTWO);

        world.generateRooms();
        world.connect();
        world.createPlayer();
        world.generateFlowers();
        ter.renderFrame(world.getWorld());

        char user;
        int count = 0;
        while (count < 1000) {
            if (StdDraw.hasNextKeyTyped()) {
                user = StdDraw.nextKeyTyped();
                world.moveAvatar(user);
                count++;
                ter.renderFrame(world.getWorld());
            }
        }
    }

    public static void startGame(String seed, World world) {
        TERenderer ter = new TERenderer();
        ter.initialize(90 , 40);

        //World world = new World(90, 40, seed);

        world.generateRooms();
        world.connect();
        world.createPlayer();
        world.generateFlowers();
        ter.renderFrame(world.getWorld());

        char user;
        int count = 0;
        while (count < 1000) {
            if (StdDraw.hasNextKeyTyped()) {
                user = StdDraw.nextKeyTyped();
                world.moveAvatar(user);
                count++;
                ter.renderFrame(world.getWorld());
            }
        }
    }
}

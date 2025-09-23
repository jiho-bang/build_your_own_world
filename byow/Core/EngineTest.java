package byow.Core;



import byow.TileEngine.TETile;

import org.junit.*;


public class EngineTest {

    @Test
    public void interactWithInputString() {
        Engine r = new Engine();
        TETile[][] check = r.interactWithInputString("n9127564470038628925sdaddawwawas:q");
        //check = r.interactWithInputString("lwasaasswadada:q");
        //check = r.interactWithInputString("ladds");
        //assertEquals(check2[0][0], Tileset.WALL);
        //assertEquals(check[0][0], Tileset.NOTHING);
        //assertEquals(check2[0][0], Tileset.NOTHING);
        //assertTrue(check.equals(check2));
        /*for (int i = 0; i < check2.length; i++) {
            for (int j = 0; j < check2[0].length; j++) {
                if(!check[i][j].equals(check2[i][j])) {
                    System.out.println(i + " " + j);
                }
            }
        }

         */
        //TERenderer ter = new TERenderer();
        //ter.initialize(90 , 40);
        //ter.renderFrame(check);

    }
}

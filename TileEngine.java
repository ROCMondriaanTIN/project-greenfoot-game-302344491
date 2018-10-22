
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roy
 */
public class TileEngine {

    public static int TILE_WIDTH;
    public static int TILE_HEIGHT;
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static int MAP_WIDTH;
    public static int MAP_HEIGHT;

    private World world;
    private int[][] map;
    private Tile[][] generateMap;
    private TileFactory tileFactory;

    public TileEngine(World world, int tileWidth, int tileHeight) {
        this.world = world;
        TILE_WIDTH = tileWidth;
        TILE_HEIGHT = tileHeight;
        SCREEN_WIDTH = world.getWidth();
        SCREEN_HEIGHT = world.getHeight();
        this.tileFactory = new TileFactory();
    }

    public TileEngine(World world, int tileWidth, int tileHeight, int[][] map) {
        this(world, tileWidth, tileHeight);
        this.setMap(map);
    }

    public void setMap(int[][] map) {
        this.map = map;
        MAP_HEIGHT = this.map.length;
        MAP_WIDTH = this.map[0].length;
        this.generateMap = new Tile[MAP_HEIGHT][MAP_WIDTH];
        this.clearTilesWorld();
        this.generateWorld();
    }

    public void setTileFactory(TileFactory tf) {
        this.tileFactory = tf;
    }

    public void clearTilesWorld() {
        List<Tile> removeObjects = this.world.getObjects(Tile.class);
        this.world.removeObjects(removeObjects);
    }

    public void generateWorld() {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                // Nummer ophalen in de int array
                int mapIcon = this.map[y][x];
                if (mapIcon == 0) {
                    continue;
                }
                // Als de mapIcon 0 is dan wordt de code hieronder overgeslagen
                // Dus er wordt geen tile aangemaakt. 0 is dus geen tile;
                Tile createdTile = this.tileFactory.createTile(mapIcon);

                addTileAt(createdTile, x, y);
            }
        }
    }

    public void addTileAt(Tile tile, int x, int y) {
        // De X en Y positie zitten het midden van de Actor. 
        // De tilemap genereerd een wereld gebaseerd op dat de X en Y
        // positie links boven in zitten. Vandaar de we de helft van de 
        // breedte en hoogte optellen zodat de X en Y links boven zit voor 
        // het toevoegen van het object.
        this.world.addObject(tile, (x * TILE_WIDTH) + TILE_WIDTH / 2, (y * TILE_HEIGHT) + TILE_HEIGHT / 2);
        // Toevoegen aan onze lokale array. Makkelijk om de tile op te halen
        // op basis van een x en y positie van de map
        this.generateMap[y][x] = tile;
    }

    public Tile getTileAt(int x, int y) {
        return this.generateMap[y][x];
    }

}

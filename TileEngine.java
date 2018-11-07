
import greenfoot.*;
import java.util.List;

/**
 *
 * @author R. Springer
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

    /**
     * Constuctor of the TileEngine
     *
     * @param world A World class or a extend of it.
     * @param tileWidth The width of the tile used in the TileFactory and
     * calculations
     * @param tileHeight The heigth of the tile used in the TileFactory and
     * calculations
     */
    public TileEngine(World world, int tileWidth, int tileHeight) {
        this.world = world;
        TILE_WIDTH = tileWidth;
        TILE_HEIGHT = tileHeight;
        SCREEN_WIDTH = world.getWidth();
        SCREEN_HEIGHT = world.getHeight();
        this.tileFactory = new TileFactory();
    }

    /**
     * Constuctor of the TileEngine
     *
     * @param world A World class or a extend of it.
     * @param tileWidth The width of the tile used in the TileFactory and
     * calculations
     * @param tileHeight The heigth of the tile used in the TileFactory and
     * calculations
     * @param map A tilemap with numbers
     */
    public TileEngine(World world, int tileWidth, int tileHeight, int[][] map) {
        this(world, tileWidth, tileHeight);
        this.setMap(map);
    }

    /**
     * The setMap method used to set a map. This method also clears the previous
     * map and generates a new one.
     *
     * @param map
     */
    public void setMap(int[][] map) {
        this.clearTilesWorld();
        this.map = map;
        MAP_HEIGHT = this.map.length;
        MAP_WIDTH = this.map[0].length;
        this.generateMap = new Tile[MAP_HEIGHT][MAP_WIDTH];
        this.generateWorld();
    }

    /**
     * The setTileFactory sets a tilefactory. You can use this if you want to
     * create you own tilefacory and use it in the class.
     *
     * @param tf A Tilefactory or extend of it.
     */
    public void setTileFactory(TileFactory tf) {
        this.tileFactory = tf;
    }

    /**
     * Removes al the tiles from the world.
     */
    public void clearTilesWorld() {
        List<Tile> removeObjects = this.world.getObjects(Tile.class);
        this.world.removeObjects(removeObjects);
        this.map = null;
        this.generateMap = null;
        MAP_HEIGHT = 0;
        MAP_WIDTH = 0;
    }

    /**
     * Creates the tile world based on the TileFactory and the map icons.
     */
    public void generateWorld() {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                // Nummer ophalen in de int array
                int mapIcon = this.map[y][x];
                if (mapIcon == -1) {
                    continue;
                }
                // Als de mapIcon -1 is dan wordt de code hieronder overgeslagen
                // Dus er wordt geen tile aangemaakt. -1 is dus geen tile;
                Tile createdTile = this.tileFactory.createTile(mapIcon);

                addTileAt(createdTile, x, y);
            }
        }
    }

    /**
     * Adds a tile on the colom and row. Calculation is based on TILE_WIDTH and
     * TILE_HEIGHT
     *
     * @param tile The Tile
     * @param colom The colom where the tile exist in the map
     * @param row The row where the tile exist in the map
     */
    public void addTileAt(Tile tile, int colom, int row) {
        // De X en Y positie zitten het midden van de Actor. 
        // De tilemap genereerd een wereld gebaseerd op dat de X en Y
        // positie links boven in zitten. Vandaar de we de helft van de 
        // breedte en hoogte optellen zodat de X en Y links boven zit voor 
        // het toevoegen van het object.
        this.world.addObject(tile, (colom * TILE_WIDTH) + TILE_WIDTH / 2, (row * TILE_HEIGHT) + TILE_HEIGHT / 2);
        // Toevoegen aan onze lokale array. Makkelijk om de tile op te halen
        // op basis van een x en y positie van de map
        this.generateMap[row][colom] = tile;
    }

    /**
     * Retrieves a tile at the location based on colom and row in the map
     *
     * @param colom
     * @param row
     * @return The tile at the location colom and row. Returns null if it cannot
     * find a tile.
     */
    public Tile getTileAt(int colom, int row) {
        try {
            return this.generateMap[row][colom];
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retrieves a tile based on a x and y position in the world
     *
     * @param x X-position in the world
     * @param y Y-position in the world
     * @return The tile at the location colom and row. Returns null if it cannot
     * find a tile.
     */
    public Tile getTileAtXY(int x, int y) {
        int col = getColumn(x);
        int row = getRow(y);

        Tile tile = getTileAt(col, row);
        return tile;
    }

    /**
     * This methode checks if a tile on a x and y position in the world is solid
     * or not.
     *
     * @param x X-position in the world
     * @param y Y-position in the world
     * @return Tile at location is solid
     */
    public boolean checkTileSolid(int x, int y) {
        Tile tile = getTileAtXY(x, y);
        if (tile != null && tile.isSolid) {
            return true;
        }
        return false;
    }

    /**
     * This methode returns a colom based on a x position.
     *
     * @param x
     * @return the colom
     */
    public int getColumn(int x) {
        return (int) Math.floor(x / TILE_WIDTH);
    }

    /**
     * This methode returns a row based on a y position.
     *
     * @param y
     * @return the row
     */
    public int getRow(int y) {
        return (int) Math.floor(y / TILE_HEIGHT);
    }

    /**
     * This methode returns a x position based on the colom
     *
     * @param col
     * @return The x position
     */
    public int getX(int col) {
        return col * TILE_WIDTH;
    }

    /**
     * This methode returns a y position based on the row
     *
     * @param row
     * @return The y position
     */
    public int getY(int row) {
        return row * TILE_HEIGHT;
    }

}

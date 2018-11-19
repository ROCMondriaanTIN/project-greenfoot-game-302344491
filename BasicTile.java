import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class BasicTile extends Actor {
    
    public boolean isSolid = false;
    public int _id;
    
    protected static int id;
    
    protected TileType type;
    protected int mapIcon;
    protected int mapID;
    protected int colom;
    protected int row;
    
    /**
     * Constructor of the tile. Creates a tile based on image, width and height
     *
     * @param image Path to the image file
     * @param width Width of the tile
     * @param heigth Height of the tile
     */
    public BasicTile(String image, int width, int heigth) {
        super();
        setImage(image);
        getImage().scale(width, heigth);
        if (CollisionEngine.DEBUG) {
            getImage().drawString("ID: " + id, 10, 10);
        }
        _id = id;
        id++;
    }
    
    /**
     * Constructor, easy to use when width and height of the tile are the same.
     * 
     * @param image Path to the image file
     * @param size width and height of the tile
     */
    public BasicTile(String image, int size) {
        this(image, size, size);
    }
    
    /**
     * Constructor for the use of a type
     * @param image Path to the image file
     * @param size width and height of the tile
     * @param type The type of the tile
     */
    public BasicTile(String image, int size, TileType type) {
        this(image, size, size);
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "id: " + _id + "\n"
                + "X: " + getX() + "\n"
                + "Y: " + getY();
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public int getMapIcon() {
        return mapIcon;
    }

    public void setMapIcon(int mapIcon) {
        this.mapIcon = mapIcon;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public int getColom() {
        return colom;
    }

    public void setColom(int colom) {
        this.colom = colom;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}

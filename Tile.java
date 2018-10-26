

import greenfoot.*;
/**
 *
 * @author Roy
 */
public class Tile extends Actor {
    
    public boolean isSolid = false;
    private static int id;
    public int _id;
    
    public Tile(String image, int width, int heigth) {
        super();
        setImage(image);
        getImage().scale(width, heigth);
        getImage().drawString("ÏD: " + id, 10, 10);
        _id = id;
        id++;
    }
    
}

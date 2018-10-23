

import greenfoot.*;
/**
 *
 * @author Roy
 */
public class Tile extends Actor {
    
    public boolean isSolid = false;
    
    public Tile(String image, int width, int heigth) {
        super();
        setImage(image);
        getImage().scale(width, heigth);
    }
}

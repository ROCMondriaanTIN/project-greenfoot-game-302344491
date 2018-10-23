import greenfoot.*;

/**
 *
 * @author Roy
 */
public class ActorCamera extends Actor {
    
    public int screenX;
    public int screenY;
    private int worldX;
    private int worldY;
    private boolean firstLocation = true;
    
    @Override
    public void setLocation(int x, int y) {
        if(firstLocation == true) {
            firstLocation = false;
            screenX = x;
            screenY = y;
        }
        super.setLocation(screenX, screenY);
        this.worldX = x;
        this.worldY = y;
    }
    
    @Override
    public int getX() {
        return this.worldX;
    }
    
    @Override
    public int getY() {
        return this.worldY;
    }
    
    public int getScreenX() {
        return this.screenX;
    }
    
    public int getScreenY() {
        return this.screenY;
    }
    
}

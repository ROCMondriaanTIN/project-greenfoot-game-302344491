
import greenfoot.*;

/**
 *
 * @author cyrie
 */
public class Enemy extends Mover {

    private int walkRange;
    private int xMin;
    private int xMax;
    private boolean firstAct;
    private int speed;
    
    public Enemy() {
        super();
        setImage("pokerMad.png");
        getImage().mirrorHorizontally();
        walkRange = 140;
        firstAct = true;
        speed = 1;
    }

    @Override
    public void act() {
        int x = getX();
        int y = getY();

        if (firstAct) {
            firstAct = false;
            xMin = x - walkRange / 2;
            xMax = x + walkRange / 2;
        }
        
        x += speed;
        if(x >= xMax) {
            speed *= -1;
            x = xMax;
            getImage().mirrorHorizontally();
        }else if(x <= xMin) {
            speed *= -1;
            x = xMin;
            getImage().mirrorHorizontally();
        }
        setLocation(x, y);
        
    }
}

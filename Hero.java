
import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Hero extends Mover {

    private final double gravity;
    private final double acc;
    private final double drag;
    private int x=185;
    private int y=493;

    public Hero() {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage("p1.png");
    }

    @Override
    public void act() {
        handleInput();
        checkpoint();
        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        } 
        applyVelocity();
        for (Actor DodelijkeLava : getObjectsInRange(84, DodelijkeLava.class)) {
            if (DodelijkeLava != null && DodelijkeLava instanceof DodelijkeLava )
            setLocation(x, y);
            break;
        }

        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                setLocation(x,y);
                break;
            }
        }
    }
    public void checkpoint()
    {
    if(isTouching(Checkpoint.class))    
    {
    x=getX();
    y=getY();
}
    }
    public void handleInput() {
        if (Greenfoot.isKeyDown("w")) {
            velocityY = -12;
        }

        if (Greenfoot.isKeyDown("a")) {
            velocityX = -2;
        } else if (Greenfoot.isKeyDown("d")) {
            velocityX = 2;
        }
    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }
    
}

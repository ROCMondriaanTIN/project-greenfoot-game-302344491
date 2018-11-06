
import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Hero extends Mover {

    private double gravity;
    private double acc;
    private double drag;
    private TileEngine te;

    public Hero(TileEngine te) {
        super();
        this.te = te;
        setImage("p1.png");
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
    }

    @Override
    public void act() {
        double x = (double) this.getX();
        double y = (double) this.getY();

        handleInput();
        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        this.applyVelocity();

        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                this.getWorld().removeObject(this);
                break;
            }
        }
    }

    public void handleInput() {
        if (Greenfoot.isKeyDown("w")) {
            velocityY = -10;
        } else if (Greenfoot.isKeyDown("s")) {
//            velY = 2;
        }

        if (Greenfoot.isKeyDown("a")) {
            velocityX = -2;
        } else if (Greenfoot.isKeyDown("d")) {
            velocityX = 2;
        }
    }

    public int getWidth() {
        return this.getImage().getWidth();
    }

    public int getHeight() {
        return this.getImage().getHeight();
    }
}

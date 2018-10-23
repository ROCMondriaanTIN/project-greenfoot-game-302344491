
import greenfoot.*;

/**
 *
 * @author Roy
 */
public class Hero extends ActorCamera {

    private double gravity;
    private double acc;
    private double velY;
    private double velX;
    private double drag;

    public Hero() {
        super();
        setImage("p1.png");
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
    }

    @Override
    public void act() {
        double x = (double) this.getX();
        double y = (double) this.getY();
        
//        velY += acc;
//        if (velY > gravity) {
//            velY = gravity;
//        }

        handleInput();
        velX *= drag;
        velY *= drag;
        y += velY;
        x += velX;

        this.setLocation((int) (x + 0.5), (int) (y + 0.5));
//        if (isColliding()) {
//            for(Actor tile : getIntersectingObjects(Tile.class)) {
//                resolveColision(tile);
//            }
//            
////            resolveColision(this.getOneIntersectingObject(Tile.class));
//        }
    }

    public void handleInput() {
        if (Greenfoot.isKeyDown("w")) {
            velY = -2;
        } else if (Greenfoot.isKeyDown("s")) {
            velY = 2;
        }

        if (Greenfoot.isKeyDown("a")) {
            velX = -2;
        } else if (Greenfoot.isKeyDown("d")) {
            velX = 2;
        }
    }

    public boolean isColliding() {
        Actor colidedTile = this.getOneIntersectingObject(Tile.class);
        if (colidedTile != null) {
            return true;
        }
        return false;
    }

    public void resolveColision(Actor colidedActor) {
        int x = getX();
        int y = getY();
        int left = this.getX() - getWidth() / 2;
        int right = this.getX() + getWidth() / 2;
        int top = this.getY() - getHeight() / 2;
        int bottom = this.getY() + getHeight() / 2;

        int leftCol = colidedActor.getX() - colidedActor.getImage().getWidth() / 2;
        int rightCol = colidedActor.getX() + colidedActor.getImage().getWidth() / 2;
        int topCol = colidedActor.getY() - colidedActor.getImage().getHeight() / 2;
        int bottomCol = colidedActor.getY() + colidedActor.getImage().getHeight() / 2;

        int offsetY = 0;
        int offsetX = 0;
        if ((right > leftCol && left < rightCol) && y < colidedActor.getY() && bottom > topCol) {
            offsetY = topCol - bottom;
        }
        if ((right > leftCol && left < rightCol) && y > colidedActor.getY() && top < bottomCol) {
            offsetY = bottomCol - top;
        }

        if ((bottom + offsetY > topCol && top + offsetY < bottomCol) && x > colidedActor.getX() && left < rightCol) {
            offsetX = left - rightCol;
        }
//        if (x < colidedActor.getX() && right > leftCol && (y > topCol && y < bottomCol)) {
//            offsetX = leftCol - right;
//        }

        x += offsetX;
        y += offsetY;
        setLocation(x, y);
    }

    public int getWidth() {
        return this.getImage().getWidth();
    }

    public int getHeight() {
        return this.getImage().getHeight();
    }
}

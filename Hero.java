
import greenfoot.*;

/**
 *
 * @author Roy
 */
public class Hero extends ActorCamera {

    private double gravity;
    private double acc;
    private int steps;
    private int jumpHeight;
    private double velY;
    private double velX;
    private double drag;
    private TileEngine te;

    public Hero(TileEngine te) {
        super();
        this.te = te;
        setImage("p1.png");
        gravity = 9.8 / 10;
        acc = 0.6;
        drag = 0.8;
        steps = 5;
        jumpHeight = 10;
    }

    @Override
    public void act() {
        double x = (double) this.getX();
        double y = (double) this.getY();


        handleInput();
        velX *= drag;
//        velY *= drag;
        if (Math.abs(velX) < 0.01) {
            velX = 0;
        }
        if (Math.abs(velY) < 0.01) {
            velY = 0;
        }
        for (int i = 0; i < steps; i++) {
            x += velX;
            this.setLocation((int) (x + 0.5), (int) (y + 0.5));
            collide(velX, velY);    
        }
        for (int j = 0; j < jumpHeight; j++) {
            y += velY;
            velY += acc;
            if (velY > gravity) {
                velY = gravity;
            }
            this.setLocation((int) (x + 0.5), (int) (y + 0.5));
            collide(velX, velY);
        }


        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                this.getWorld().removeObject(this);
                break;
            }
        }

//        if (isColliding()) {
//            for(Actor tile : getIntersectingObjects(Tile.class)) {
//                resolveColision(tile);
//            }
//            
////            resolveColision(this.getOneIntersectingObject(Tile.class));
//        }
    }

    private void collide(double dirX, double dirY) {
        int top = CollisionEngine.getActorTop((Actor) this);
        int left = CollisionEngine.getActorLeft((Actor) this);
        int right = CollisionEngine.getActorRight((Actor) this);
        int bottom = CollisionEngine.getActorBottom((Actor) this);

        boolean collide = checkTile(left, top)
                || checkTile(left, bottom)
                || checkTile(right, bottom)
                || checkTile(right, top);
        if (!collide) {
            return;
        }

        int row;
        int col;
        int x = getX();
        int y = getY();
        System.out.println("DirX: " + dirX);
        System.out.println("DirY: " + dirY);
        if (dirY > 0) {
            velY = 0;
            row = te.getRow(bottom);
            y = -this.getHeight() / 2 + te.getY(row);
        } else if (dirY < 0) {
            velY = 0;
            row = te.getRow(top);
            y = this.getHeight() / 2 + te.getY(row + 1);
        } else if (dirX > 0) {
            velX = 0;
            col = te.getColumn(right);
            x = -this.getWidth() / 2 + te.getX(col);
        } else if (dirX < 0) {
            velX = 0;
            col = te.getColumn(left);
            x = this.getWidth() / 2 + te.getX(col + 1);
        }
        this.setLocation(x, y);
    }

    private boolean checkTile(int x, int y) {
        int col = (int) Math.floor(x / TileEngine.TILE_WIDTH);
        int row = (int) Math.floor(y / TileEngine.TILE_HEIGHT);

        Tile tile = te.getTileAt(col, row);
        if (tile != null && tile.isSolid) {
            return true;
        }
        return false;
    }

    public void handleInput() {
        if (Greenfoot.isKeyDown("w")) {
            velY = -10;
        } else if (Greenfoot.isKeyDown("s")) {
//            velY = 2;
        }

        if (Greenfoot.isKeyDown("a")) {
            velX = -1;
        } else if (Greenfoot.isKeyDown("d")) {
            velX = 1;
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

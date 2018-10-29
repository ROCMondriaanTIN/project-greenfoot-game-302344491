
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

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
    private Camera camera;

    public Hero(TileEngine te, Camera camera) {
        super();
        this.te = te;
        setImage("p1.png");
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        steps = 5;
        jumpHeight = 10;
        this.camera = camera;
    }

    @Override
    public void act() {
        double x = (double) this.getX();
        double y = (double) this.getY();

        handleInput();

//        velY *= drag;
        if (Math.abs(velX) < 0.01) {
            velX = 0;
        }
        if (Math.abs(velY) < 0.01) {
            velY = 0;
        }
        velX *= drag;
        x += velX;
        y += velY;
        velY += acc;
        if (velY > gravity) {
            velY = gravity;
        }

        this.setLocation((int) (x + 0.5), (int) (y + 0.5));
//        collide();

        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                this.getWorld().removeObject(this);
                break;
            }
        }
    }

    private void collide() {
        int top = CollisionEngine.getActorTop((Actor) this);
        int left = CollisionEngine.getActorLeft((Actor) this);
        int right = CollisionEngine.getActorRight((Actor) this);
        int bottom = CollisionEngine.getActorBottom((Actor) this);

        List<Tile> collidingTiles = getCollidingTiles(top, left, right, bottom, this.getX(), this.getY());
        if (collidingTiles.isEmpty()) {
            return;
        }
        Tile tile = collidingTiles.get(0);
        if (tile == null) {
            System.out.println("Tile is null");
            System.out.println(tile);
            return;
        }
        int x = getX();
        int y = getY();

        System.out.println("TileID: " + tile._id);
        System.out.println("Camera y: " + camera.getY());
        int topTile = CollisionEngine.getActorTop(tile) + camera.getY();
        int bottomTile = CollisionEngine.getActorBottom(tile) + camera.getY();
        int leftTile = CollisionEngine.getActorLeft(tile) + camera.getX();
        int rightTile = CollisionEngine.getActorRight(tile) + camera.getX();

        double overlapX = 0;
        double overlapY = 0;
        if (bottom > topTile && top < bottomTile) {
            if (velY >= 0) {
                overlapY = topTile - bottom;
            } else {
                overlapY = bottomTile - top;
            }
        }

        if (right > leftTile && left < rightTile) {
            if (velX >= 0) {
                overlapX = leftTile - right;
            } else {
                overlapX = rightTile - left;

            }
        }

        if (Math.abs(overlapY) > Math.abs(overlapX)) {
            x += overlapX;
        } else {
            velY = 0;
            y += overlapY;
        }
        setLocation(x, y);
    }

    private List<Tile> getCollidingTiles(int top, int left, int right, int bottom, int midX, int midY) {
        List<Tile> tiles = new ArrayList<>();

        if (checkTile(left, top)) {
            tiles.add(getTileAtXY(left, top));
        }
        if (checkTile(left, bottom)) {
            tiles.add(getTileAtXY(left, bottom));
        }
        if (checkTile(right, bottom)) {
            tiles.add(getTileAtXY(right, bottom));
        }
        if (checkTile(right, top)) {
            tiles.add(getTileAtXY(right, top));
        }
        if (checkTile(midX, top)) {
            Tile tile = getTileAtXY(midX, top);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        if (checkTile(midX, bottom)) {
            Tile tile = getTileAtXY(midX, bottom);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        if (checkTile(left, midY)) {
            Tile tile = getTileAtXY(left, midY);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        if (checkTile(right, midY)) {
            Tile tile = getTileAtXY(left, midY);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    private Tile getTileAtXY(int x, int y) {
        int col = (int) Math.floor(x / TileEngine.TILE_WIDTH);
        int row = (int) Math.floor(y / TileEngine.TILE_HEIGHT);

        Tile tile = te.getTileAt(col, row);
        return tile;
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

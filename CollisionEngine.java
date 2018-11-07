
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author R. Springer
 */
public class CollisionEngine {

    
    public static final boolean DEBUG = false;
    private final Camera camera;
    private final TileEngine tileEngine;
    private final List<Mover> collidingActors;

    /**
     * The constructor of the CollisionEngine.
     *
     * @param tileEngine The TileEngine
     * @param camera The camera
     */
    public CollisionEngine(TileEngine tileEngine, Camera camera) {
        this.tileEngine = tileEngine;
        collidingActors = new ArrayList<>();
        this.camera = camera;
    }

    /**
     * This methode lets you add a Mover that will be in this collision engine.
     * When you add a mover it will collid with all the tiles in the world.
     *
     * @param mover A Mover class or a extend of it.
     */
    public void addCollidingMover(Mover mover) {
        this.collidingActors.add(mover);
    }

    /**
     * This methode will remove the Mover from the collision engine
     *
     * @param mover A Mover class or a extend of it.
     */
    public void removeCollidingActor(Mover mover) {
        if (this.collidingActors.contains(mover)) {
            this.collidingActors.remove(mover);
        }
    }

    /**
     * This methode must be called every update of the game. Else the collision
     * will not apply correctly
     */
    public void update() {
        for (Mover mover : this.collidingActors) {
            int actorLeft = getActorLeft(mover);
            int actorRight = getActorRight(mover);
            int actorTop = getActorTop(mover);
            int actorBottom = getActorBottom(mover);
            List<Tile> tiles = getCollidingTiles(actorTop, actorLeft, actorRight, actorBottom, mover.getX(), mover.getY());

            if (!tiles.isEmpty()) {
                for (Tile tile : tiles) {
                    boolean resolved = resolve(mover, tile);
//                    if(resolved) {
//                        break;
//                    }
                }
            }
        }
    }

    /**
     * This methode will detect if a Mover is overlapping with the tiles
     *
     * @param mover A Mover class or a extend of it.
     * @return Returns true if the mover is overlapping
     */
    public boolean detect(Mover mover) {
        int actorLeft = getActorLeft(mover);
        int actorRight = getActorRight(mover);
        int actorTop = getActorTop(mover);
        int actorBottom = getActorBottom(mover);

        return this.detect(mover, actorLeft, actorRight, actorTop, actorBottom);
    }

    /**
     * This methode will detect if a Mover is overlapping with the tiles
     *
     * @param mover A Mover class or a extend of it.
     * @param actorLeft The far most left x position of the Mover.
     * @param actorRight The far most right x position of the Mover.
     * @param actorTop The far most top y position of the Mover.
     * @param actorBottom The far most bottom y position of the Mover.
     * @return
     */
    public boolean detect(Mover mover, int actorLeft, int actorRight, int actorTop, int actorBottom) {
        return !getCollidingTiles(actorTop, actorLeft, actorRight, actorBottom, mover.getX(), mover.getY()).isEmpty();
    }

    /**
     * This methode will get all the tiles at the different x and y position
     *
     * @param top The far most top y position
     * @param left The far most left x position
     * @param right The far most right x position
     * @param bottom The far most bottom y position
     * @param midX The middle x position
     * @param midY The middle y position
     * @return Returns a list of tiles that are located on those positions.
     */
    private List<Tile> getCollidingTiles(int top, int left, int right, int bottom, int midX, int midY) {
        List<Tile> tiles = new ArrayList<>();

        if (tileEngine.checkTileSolid(left, top)) {
            Tile tile = tileEngine.getTileAtXY(left, top);
            tiles.add(tile);
        }
        if (tileEngine.checkTileSolid(left, bottom)) {
            Tile tile = tileEngine.getTileAtXY(left, bottom);
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(right, bottom)) {
            Tile tile = tileEngine.getTileAtXY(right, bottom);
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(right, top)) {
            Tile tile = tileEngine.getTileAtXY(right, top);
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(midX, top)) {
            Tile tile = tileEngine.getTileAtXY(midX, top);
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(midX, bottom)) {
            Tile tile = tileEngine.getTileAtXY(midX, bottom);
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(left, midY)) {
            Tile tile = tileEngine.getTileAtXY(left, midY);
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(right, midY)) {
            Tile tile = tileEngine.getTileAtXY(right, midY);
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    /**
     * This methode will resolves the overlapping.
     *
     * @param mover A Mover class or a extend of it.
     * @param tile A Tile class or a extend of it.
     * @return Returns a true if the overlap was resolved.
     */
    public boolean resolve(Mover mover, Tile tile) {
        int left = getActorLeft(mover);
        int right = getActorRight(mover);
        int top = getActorTop(mover);
        int bottom = getActorBottom(mover);
        int x = mover.getX();
        int y = mover.getY();
        int topTile = CollisionEngine.getActorTop(tile) + camera.getY();
        int bottomTile = CollisionEngine.getActorBottom(tile) + camera.getY();
        int leftTile = CollisionEngine.getActorLeft(tile) + camera.getX();
        int rightTile = CollisionEngine.getActorRight(tile) + camera.getX();

        double overlapX = 0;
        double overlapY = 0;

        if (bottom > topTile && top < bottomTile) {
            if (mover.velocityY >= 0) {
                overlapY = topTile - bottom;
            } else {
                overlapY = bottomTile - top;
            }
        }

        if (right > leftTile && left < rightTile) {
            if (mover.velocityX >= 0) {
                overlapX = leftTile - right;
            } else {
                overlapX = rightTile - left;
            }
        }

        if (DEBUG) {
            System.out.println("Player:\n" + mover);
            System.out.println("Tile:\n" + tile);
        }

        if (Math.abs(overlapX) > 0 && Math.abs(overlapY) > 0) {
            if (Math.abs(overlapY) > Math.abs(overlapX)) {
                mover.velocityX = 0;
                x += overlapX;
            } else {
                mover.velocityY = 0;
                y += overlapY;
            }
            mover.setLocation(x, y);
            return true;
        }
        return false;
    }

    /**
     * Calculate the half width of a Actor
     *
     * @param actor An Actor class or an extend of it.
     * @return returns the half width
     */
    public static int getActorHalfWidth(Actor actor) {
        return actor.getImage().getWidth() / 2;
    }

    /**
     * Calculate the half hieght of a Actor
     *
     * @param actor An Actor class or an extend of it.
     * @return returns the half height
     */
    public static int getActorHalfHeigth(Actor actor) {
        return actor.getImage().getHeight() / 2;
    }

    /**
     * Calculate the top Y position of the actor
     *
     * @param actor An Actor class or an extend of it.
     * @return return top Y actor position
     */
    public static int getActorTop(Actor actor) {
        return actor.getY() - actor.getImage().getHeight() / 2;
    }

    /**
     * Calculate the bottom Y position of the actor
     *
     * @param actor An Actor class or an extend of it.
     * @return return bottom Y actor position
     */
    public static int getActorBottom(Actor actor) {
        return actor.getY() + actor.getImage().getHeight() / 2;
    }

    /**
     * Calculate the left X position of the actor
     *
     * @param actor An Actor class or an extend of it.
     * @return return left X actor position
     */
    public static int getActorLeft(Actor actor) {
        return actor.getX() - actor.getImage().getWidth() / 2;
    }

    /**
     * Calculate the right X position of the actor
     *
     * @param actor An Actor class or an extend of it.
     * @return return right X actor position
     */
    public static int getActorRight(Actor actor) {
        return actor.getX() + actor.getImage().getWidth() / 2;
    }
}

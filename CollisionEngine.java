
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cyrie
 */
public class CollisionEngine {

    private World world;
    private Camera camera;
    private TileEngine tileEngine;
    private List<Mover> collidingActors;

    public CollisionEngine(World world, TileEngine tileEngine, Camera camera) {
        this.world = world;
        this.tileEngine = tileEngine;
        collidingActors = new ArrayList<>();
        this.camera = camera;
    }

    public void addCollidingMover(Mover a) {
        this.collidingActors.add(a);
    }

    public void removeCollidingActor(Mover a) {
        if (this.collidingActors.contains(a)) {
            this.collidingActors.remove(a);
        }
    }

    public void update() {
        for (Mover actor : this.collidingActors) {
            if (detect(actor)) {
                int actorLeft = getActorLeft(actor);
                int actorRight = getActorRight(actor);
                int actorTop = getActorTop(actor);
                int actorBottom = getActorBottom(actor);
                List<Tile> tiles = getCollidingTiles(actorTop, actorLeft, actorRight, actorBottom, actor.getX(), actor.getY());
                for (Tile tile : tiles) {
                    boolean resolved = resolve(actor, tile);
                    if(resolved) {
                        break;
                    }
                }
            }
        }
    }

    public boolean detect(Mover actor) {
        int actorLeft = getActorLeft(actor);
        int actorRight = getActorRight(actor);
        int actorTop = getActorTop(actor);
        int actorBottom = getActorBottom(actor);

        return this.detect(actor, actorLeft, actorRight, actorTop, actorBottom);
    }

    public boolean detect(Mover actor, int actorLeft, int actorRight, int actorTop, int actorBottom) {
        return !getCollidingTiles(actorTop, actorLeft, actorRight, actorBottom, actor.getX(), actor.getY()).isEmpty();
    }

    private List<Tile> getCollidingTiles(int top, int left, int right, int bottom, int midX, int midY) {
        List<Tile> tiles = new ArrayList<>();

        if (tileEngine.checkTileSolid(left, top)) {
            Tile tile = tileEngine.getTileAtXY(left, top);
            tiles.add(tile);
        }
        if (tileEngine.checkTileSolid(left, bottom)) {
            Tile tile = tileEngine.getTileAtXY(left, bottom);
            if(!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(right, bottom)) {
            Tile tile = tileEngine.getTileAtXY(right, bottom);
            if(!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(right, top)) {
            Tile tile = tileEngine.getTileAtXY(right, top);
            if(!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(midX, top)) {
            Tile tile = tileEngine.getTileAtXY(midX, top);
            if(!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(midX, bottom)) {
            Tile tile = tileEngine.getTileAtXY(midX, bottom);
            if(!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(left, midY)) {
            Tile tile = tileEngine.getTileAtXY(left, midY);
            if(!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(right, midY)) {
            Tile tile = tileEngine.getTileAtXY(right, midY);
            if(!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public boolean resolve(Mover actor, Tile tile) {
        int left = getActorLeft(actor);
        int right = getActorRight(actor);
        int top = getActorTop(actor);
        int bottom = getActorBottom(actor);
        int x = actor.getX();
        int y = actor.getY();
        int topTile = CollisionEngine.getActorTop(tile) + camera.getY();
        int bottomTile = CollisionEngine.getActorBottom(tile) + camera.getY();
        int leftTile = CollisionEngine.getActorLeft(tile) + camera.getX();
        int rightTile = CollisionEngine.getActorRight(tile) + camera.getX();

        double overlapX = 0;
        double overlapY = 0;

        if (bottom > topTile && top < bottomTile) {
            if (actor.velocityY >= 0) {
                overlapY = topTile - bottom;
            } else {
                overlapY = bottomTile - top;
            }
        }

        if (right > leftTile && left < rightTile) {
            if (actor.velocityX >= 0) {
                overlapX = leftTile - right;
            } else {
                overlapX = rightTile - left;
            }
        }
        if(Math.abs(overlapX) > 0 && Math.abs(overlapY) > 0){            
            if (Math.abs(overlapY) > Math.abs(overlapX)) {
                x += overlapX;
            } else {
                actor.velocityY = 0;
                y += overlapY;
            }
            actor.setLocation(x, y);
            return true;
        }
        return false;
    }

    public static int getActorHalfWidth(Actor a) {
        return a.getImage().getWidth() / 2;
    }

    public static int getActorHalfHeigth(Actor a) {
        return a.getImage().getHeight() / 2;
    }

    public static int getActorTop(Actor a) {
        return a.getY() - a.getImage().getHeight() / 2;
    }

    public static int getActorBottom(Actor a) {
        return a.getY() + a.getImage().getHeight() / 2;
    }

    public static int getActorLeft(Actor a) {
        return a.getX() - a.getImage().getWidth() / 2;
    }

    public static int getActorRight(Actor a) {
        return a.getX() + a.getImage().getWidth() / 2;
    }
}

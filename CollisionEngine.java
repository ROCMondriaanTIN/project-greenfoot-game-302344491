
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
    private List<Actor> collidingActors;

    public CollisionEngine(World world, TileEngine tileEngine, Camera camera) {
        this.world = world;
        this.tileEngine = tileEngine;
        collidingActors = new ArrayList<>();
        this.camera = camera;
    }

    public void addCollidingActor(Actor a) {
        this.collidingActors.add(a);
    }

    public void removeCollidingActor(Actor a) {
        if (this.collidingActors.contains(a)) {
            this.collidingActors.remove(a);
        }
    }

    public void update() {
        for (Actor actor : this.collidingActors) {
//            System.out.println("ActorLeft" + getActorLeft(actor));
//            System.out.println("FirstTile Right" + getActorRight(tileEngine.getTileAt(5, 3)));

//                System.out.println("Collision found");
//            if (actor instanceof ActorCamera) {
//                ActorCamera actorCamera = (ActorCamera) actor;
////                System.out.println("ActorCameraLeft" + getActorLeft(actorCamera));
//                if (detect(actorCamera)) {
//                    resolve(actorCamera);
//                }
//            } else {
            if (detect(actor)) {
                resolve(actor);
            }
//            }
        }
    }

    public boolean detect(Actor actor) {
        int actorLeft = getActorLeft(actor);
        int actorRight = getActorRight(actor);
        int actorTop = getActorTop(actor);
        int actorBottom = getActorBottom(actor);

        return this.detect(actor, actorLeft, actorRight, actorTop, actorBottom);
    }

//    public boolean detect(ActorCamera actor) {
//        int actorLeft = getActorLeft(actor);
//        int actorRight = getActorRight(actor);
//        int actorTop = getActorTop(actor);
//        int actorBottom = getActorBottom(actor);
//
//        return this.detect(actor, actorLeft, actorRight, actorTop, actorBottom);
//    }
    public boolean detect(Actor actor, int actorLeft, int actorRight, int actorTop, int actorBottom) {
        return !getCollidingTiles(actorTop, actorLeft, actorRight, actorBottom, actor.getX(), actor.getY()).isEmpty();
    }

    private List<Tile> getCollidingTiles(int top, int left, int right, int bottom, int midX, int midY) {
        List<Tile> tiles = new ArrayList<>();

        if (tileEngine.checkTileSolid(left, top)) {
            tiles.add(tileEngine.getTileAtXY(left, top));
        }
        if (tileEngine.checkTileSolid(left, bottom)) {
            tiles.add(tileEngine.getTileAtXY(left, bottom));
        }
        if (tileEngine.checkTileSolid(right, bottom)) {
            tiles.add(tileEngine.getTileAtXY(right, bottom));
        }
        if (tileEngine.checkTileSolid(right, top)) {
            tiles.add(tileEngine.getTileAtXY(right, top));
        }
        if (tileEngine.checkTileSolid(midX, top)) {
            Tile tile = tileEngine.getTileAtXY(midX, top);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(midX, bottom)) {
            Tile tile = tileEngine.getTileAtXY(midX, bottom);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(left, midY)) {
            Tile tile = tileEngine.getTileAtXY(left, midY);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        if (tileEngine.checkTileSolid(right, midY)) {
            Tile tile = tileEngine.getTileAtXY(left, midY);
            if (tiles.indexOf(tile) == -1) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public void resolve(Actor actor) {
        int actorLeft = getActorLeft(actor);
        int actorRight = getActorRight(actor);
        int actorTop = getActorTop(actor);
        int actorBottom = getActorBottom(actor);

        this.resolve(actor, actorLeft, actorRight, actorTop, actorBottom);
    }

//    public void resolve(ActorCamera actor) {
//        int actorLeft = getActorLeft(actor);
//        int actorRight = getActorRight(actor);
//        int actorTop = getActorTop(actor);
//        int actorBottom = getActorBottom(actor);
//
//        this.resolve(actor, actorLeft, actorRight, actorTop, actorBottom);
//    }
    public void resolve(Actor actor, int left, int right, int top, int bottom) {
        List<Tile> collidingTiles = getCollidingTiles(top, left, right, bottom, actor.getX(), actor.getY());
        if (collidingTiles.isEmpty()) {
            return;
        }
        Tile tile = collidingTiles.get(0);
        if (tile == null) {
            System.out.println("Tile is null");
            System.out.println(tile);
            return;
        }
        int x = actor.getX();
        int y = actor.getY();

        System.out.println("TileID: " + tile._id);
        System.out.println("Camera y: " + camera.getY());
        int topTile = CollisionEngine.getActorTop(tile) + camera.getY();
        int bottomTile = CollisionEngine.getActorBottom(tile) + camera.getY();
        int leftTile = CollisionEngine.getActorLeft(tile) + camera.getX();
        int rightTile = CollisionEngine.getActorRight(tile) + camera.getX();

        double overlapX = 0;
        double overlapY = 0;

        if (right > leftTile && left < rightTile) {
            if (Math.abs(leftTile - right) < Math.abs(rightTile - left)) {
                overlapX = leftTile - right;
            } else {
                overlapX = rightTile - left;
            }
        }
        System.out.println("Overlapx: " + overlapX);
        if (Math.abs(overlapX) > 0) {
            if (bottom > topTile && top < bottomTile) {
                System.out.println("Math abs");
                System.out.println(Math.abs(topTile - bottom));
                System.out.println(Math.abs(bottomTile - top));
                if (Math.abs(topTile - bottom) < Math.abs(bottomTile - top)) {
                    overlapY = topTile - bottom;
                } else {
                    overlapY = bottomTile - top;
                }
            }
            System.out.println("Overlapy: " + overlapY);
            if (Math.abs(overlapY) > 0) {
                if (Math.abs(overlapY) > Math.abs(overlapX)) {
                    x += overlapX;
                } else {
                    y += overlapY;
                }
                actor.setLocation(x, y);
            }
        }

        

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

    public static int getActorTop(ActorCamera a) {
        return a.screenY - a.getImage().getHeight() / 2;
    }

    public static int getActorBottom(ActorCamera a) {
        return a.screenY + a.getImage().getHeight() / 2;
    }

    public static int getActorLeft(ActorCamera a) {
        return a.screenX - a.getImage().getWidth() / 2;
    }

    public static int getActorRight(ActorCamera a) {
        return a.screenX + a.getImage().getWidth() / 2;
    }

}

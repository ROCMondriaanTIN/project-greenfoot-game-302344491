
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cyrie
 */
public class CollisionEngine {

    private World world;
    private TileEngine tileEngine;
    private List<Actor> collidingActors;

    public CollisionEngine(World world, TileEngine tileEngine) {
        this.world = world;
        this.tileEngine = tileEngine;
        collidingActors = new ArrayList<>();
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
            System.out.println("ActorLeft" + getActorLeft(actor));
            System.out.println("FirstTile Right" + getActorRight(tileEngine.getTileAt(5, 3)));

//                System.out.println("Collision found");
            if (actor instanceof ActorCamera) {
                ActorCamera actorCamera = (ActorCamera) actor;
                System.out.println("ActorCameraLeft" + getActorLeft(actorCamera));
                if (detect(actorCamera)) {
                    resolve(actorCamera);
                }
            } else {
                if (detect(actor)) {
                    resolve(actor);
                }
            }
        }
    }

    public boolean detect(Actor actor) {
        int actorLeft = getActorLeft(actor);
        int actorRight = getActorRight(actor);
        int actorTop = getActorTop(actor);
        int actorBottom = getActorBottom(actor);

        return this.detect(actor, actorLeft, actorRight, actorTop, actorBottom);
    }

    public boolean detect(ActorCamera actor) {
        int actorLeft = getActorLeft(actor);
        int actorRight = getActorRight(actor);
        int actorTop = getActorTop(actor);
        int actorBottom = getActorBottom(actor);

        return this.detect(actor, actorLeft, actorRight, actorTop, actorBottom);
    }

    public boolean detect(Actor actor, int actorLeft, int actorRight, int actorTop, int actorBottom) {
        boolean anyCollision = false;
        for (int y = 0; y < TileEngine.MAP_HEIGHT; y++) {
            for (int x = 0; x < TileEngine.MAP_WIDTH; x++) {
                Tile currentTile = this.tileEngine.getTileAt(x, y);
                if (currentTile == null || currentTile.isSolid == false) {
                    continue;
                }
                System.out.println("Current Tile: " + currentTile);
                int tileLeft = getActorLeft(currentTile);
                int tileRight = getActorRight(currentTile);
                int tileTop = getActorTop(currentTile);
                int tileBottom = getActorBottom(currentTile);

                boolean x_overlaps = (actorLeft < tileRight) && (actorRight > tileLeft);
                boolean y_overlaps = (actorTop < tileBottom) && (actorBottom > tileTop);
                boolean collision = x_overlaps && y_overlaps;
                if (collision) {
                    System.out.println("Actor positions" + " " + actorLeft + " " + actorRight + " " + actorTop + " " + actorBottom);
                    System.out.println("Tile positions" + " " + tileLeft + " " + tileRight + " " + tileTop + " " + tileBottom);

                    anyCollision = true;
                }
            }
        }
        return anyCollision;
    }

    public void resolve(Actor actor) {
        int actorLeft = getActorLeft(actor);
        int actorRight = getActorRight(actor);
        int actorTop = getActorTop(actor);
        int actorBottom = getActorBottom(actor);

        this.resolve(actor, actorLeft, actorRight, actorTop, actorBottom);
    }

    public void resolve(ActorCamera actor) {
        int actorLeft = getActorLeft(actor);
        int actorRight = getActorRight(actor);
        int actorTop = getActorTop(actor);
        int actorBottom = getActorBottom(actor);

        this.resolve(actor, actorLeft, actorRight, actorTop, actorBottom);
    }

    public void resolve(Actor actor, int actorLeft, int actorRight, int actorTop, int actorBottom) {
        for (int y = 0; y < TileEngine.MAP_HEIGHT; y++) {
            for (int x = 0; x < TileEngine.MAP_WIDTH; x++) {
                Tile currentTile = this.tileEngine.getTileAt(x, y);
                if (currentTile == null || currentTile.isSolid == false) {
                    continue;
                }

                int tileLeft = getActorLeft(currentTile);
                int tileRight = getActorRight(currentTile);
                int tileTop = getActorTop(currentTile);
                int tileBottom = getActorBottom(currentTile);

                int actorX = actor.getX();
                int actorY = actor.getY();

                if (actorLeft <= tileRight) {
                    actorX = tileRight + (getActorHalfWidth(actor) + 10);
                } else if (actorRight >= tileLeft) {
                    actorX = tileLeft - (getActorHalfWidth(actor) + 1);
                }
                actor.setLocation(actorX, actorY);
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

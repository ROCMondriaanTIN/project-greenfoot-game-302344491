
import greenfoot.*;
import java.util.List;

/**
 *
 * @author R. Springer
 */
public class DemoHero extends Mover {

    private final double gravity;
    private final double acc;
    private final double drag;
    private CollisionEngine collisionEngine;
    private TileEngine tileEngine;

    public DemoHero(CollisionEngine collisionEngine, TileEngine tileEngine) {
        super();
        this.collisionEngine = collisionEngine;
        this.tileEngine = tileEngine;
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage("p1.png");
    }

    @Override
    public void act() {
        handleInput();

        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        applyVelocity();

        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                getWorld().removeObject(this);
                // Met return eindig je deze methode en loop
                // Aangezien wij niet meer in de world zitten willen wij geen
                // Methodes met de world of andere checks uitvoeren.
                return;
            }
        }

        List<Tile> tiles = collisionEngine.getCollidingTiles(this, true);

        for (Tile tile : tiles) {
            if (tile != null) {
                if (tile.type == TileType.WATER) {
                    getWorld().removeObject(this);
                    // Met return eindig je deze methode en loop
                    // Aangezien wij niet meer in de world zitten willen wij geen
                    // Methodes met de world of andere checks uitvoeren.
                    return;
                } else if (tile.type == TileType.BOX) {
                    // Op deze manier kan je nu tiles verwijderen
                    // Zie ook de andere removeTile methodes die zijn toegevoegd
                    tileEngine.removeTile(tile);
                }
            }
        }

    }

    public void handleInput() {
        if (Greenfoot.isKeyDown("w")) {
            velocityY = -20;
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


import greenfoot.*;

/**
 *
 * @author Roy
 */
public class Camera extends Actor {

    public static int SPEED = 10;
    private int width;
    private int height;
    private int maxX;
    private int maxY;
    private TileEngine tileEngine;
    private double cameraDrag;
    private double dirX;
    private double dirY;
    private boolean follow;
    private ActorCamera followActor;
    private double screenX;
    private double screenY;

    public Camera(TileEngine tileEngine) {
        this.width = TileEngine.SCREEN_WIDTH;
        this.height = TileEngine.SCREEN_HEIGHT;
        this.setLocation(0, 0);
        this.maxX = TileEngine.MAP_WIDTH * TileEngine.TILE_WIDTH - this.width;
        this.maxY = TileEngine.MAP_HEIGHT * TileEngine.TILE_HEIGHT - this.height;
        this.tileEngine = tileEngine;
        this.cameraDrag = 0.9;
        this.setImage(new GreenfootImage(1, 1));
    }

    public Camera(TileEngine tileEngine, int speed) {
        this(tileEngine);
        SPEED = speed;
    }

    public void follow(ActorCamera actor) {
        this.follow = true;
        this.followActor = actor;
        this.followActor.screenX = 0;
        this.followActor.screenY = 0;
    }

    @Override
    public void act() {
        dirX *= cameraDrag;
        dirY *= cameraDrag;

        int x = this.getX();
        int y = this.getY();

        if (Greenfoot.isKeyDown("e")) {
            follow = !follow;
        }

        if (follow) {
            this.followActor.screenX = this.width / 2;
            this.followActor.screenY = this.height / 2;
            
            x = this.followActor.getX() - this.width / 2;
            y = this.followActor.getY() - this.height / 2;
            
            x = Math.max(0, Math.min(x, this.maxX));
            y = Math.max(0, Math.min(y, this.maxY));
                        
            this.setLocation(x, y);

//          left and right sides
            if (this.followActor.getX() < this.width / 2
                    || this.followActor.getX() > this.maxX + this.width / 2) {
                
                this.followActor.screenX = this.followActor.getX() - this.getX();    
            }
            
//          top and bottom sides
            if (this.followActor.getY() < this.height / 2
                    || this.followActor.getY() > this.maxY + this.height / 2) {
                this.followActor.screenY = this.followActor.getY() - this.getY();
            }
        } else {
            if (Greenfoot.isKeyDown("UP")) {
                dirY = -1;
            } else if (Greenfoot.isKeyDown("DOWN")) {
                dirY = 1;
            }
            if (Greenfoot.isKeyDown("LEFT")) {
                dirX = -1;
            } else if (Greenfoot.isKeyDown("RIGHT")) {
                dirX = 1;
            }
            this.move(dirX, dirY);
        }

        this.updateView();
    }

    public void move(double dirX, double dirY) {
        int x = this.getX();
        int y = this.getY();

        x += dirX * SPEED;
        y += dirY * SPEED;

        x = Math.max(0, Math.min(x, this.maxX));
        y = Math.max(0, Math.min(y, this.maxY));
        this.setLocation(x, y);
        System.out.println("Move at location: " + getX()+ ", " + getY());
    }

    public void updateView() {
        int startCol = (int) Math.floor(this.getY() / TileEngine.TILE_HEIGHT);
        int endCol = startCol + (this.height / TileEngine.TILE_HEIGHT);
        int startRow = (int) Math.floor(this.getX() / TileEngine.TILE_WIDTH);
        int endRow = startRow + (this.width / TileEngine.TILE_WIDTH);
        int offsetX = -this.getX() + startCol * TileEngine.TILE_WIDTH;
        int offsetY = -this.getY() + startRow * TileEngine.TILE_HEIGHT;
        
        Tile currentTile = null;
        int x = 0;
        int y = 0;
        for (y = 0; y < TileEngine.MAP_HEIGHT; y++) {
            for (x = 0; x < TileEngine.MAP_WIDTH; x++) {
                currentTile = tileEngine.getTileAt(x, y);
                if (currentTile == null) {
                    continue;
                }

                int xPos = ((x - startCol) * TileEngine.TILE_WIDTH + offsetX) + (TileEngine.TILE_WIDTH / 2);
                int yPos = ((y - startRow) * TileEngine.TILE_HEIGHT + offsetY) + (TileEngine.TILE_HEIGHT / 2);
                currentTile.setLocation(xPos, yPos);
            }
        }

        if (!this.follow) {
            this.followActor.screenX = this.followActor.getX() - this.getX();
            this.followActor.screenY = this.followActor.getY() - this.getY();
        }
        
        for (ActorCamera actor : this.getWorld().getObjects(ActorCamera.class)) {
            if(actor == this.followActor) continue;
            actor.screenX = actor.getX() - this.getX();
            actor.screenY = actor.getY() - this.getY(); 
        }
    }
}

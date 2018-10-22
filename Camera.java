
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
//        this.setImage(new GreenfootImage(1, 1));
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

//            dirX = x - followActor.getX();
//            dirY = y - followActor.getY();
//            if(dirX >= 1){
//                dirX = 1;
//            }else if(dirX <= -1) {
//                dirX = -1;
//            }
//            if(dirY >= 1){
//                dirY = 1;
//            }else if(dirY <= -1) {
//                dirY = -1;
//            }
//            this.move(dirX, dirY);
            x = this.followActor.getX() - this.width / 2;
            y = this.followActor.getY() - this.height / 2;
            
            System.out.print("FollowX: " + this.followActor.getX());
            System.out.print(" FollowY: " + this.followActor.getY());
            System.out.print(" Width: " + this.width);
            System.out.println(" Height: " + this.height);
            System.out.println(" maxX: " + this.maxX);
            System.out.println(" maxY: " + this.maxY);
            x = Math.max(0, Math.min(x, this.maxX));
            y = Math.max(0, Math.min(y, this.maxY));
            
            System.out.print("x: " + x);
            System.out.println(" y: " + y);
            
            this.setLocation(x, y);

            if (this.followActor.getX() < this.width / 2
                    || this.followActor.getX() > this.maxX + this.width / 2) {
                
                System.out.println("Hero X: " + this.followActor.getX());
                System.out.println("Camera X: " + this.getX());
                this.followActor.screenX = this.followActor.getX() - this.getX();
                
            }
//             top and bottom sides
            if (this.followActor.getY() < this.height / 2
                    || this.followActor.getY() > this.maxY + this.height / 2) {
                System.out.println("Hero Y: " + this.followActor.getY());
                System.out.println("Camera Y: " + this.getY());
                this.followActor.screenY = this.followActor.getY() - this.getY();
            }
            
            System.out.print("ScreenX: " + this.followActor.screenX);
            System.out.println(" ScreenY: " + this.followActor.screenY);
            
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

        System.out.print("Offset X: " + offsetX );
        System.out.println(" dOffset Y: " + offsetY );
        
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

        if (this.follow) {
            int xPos = this.followActor.screenX;// - this.followActor.getImage().getWidth() / 2;
            int yPos = this.followActor.screenY;// - this.followActor.getImage().getHeight() / 2;
//            this.followActor.setLocation(xPos, yPos);
            this.followActor.setLocation(xPos, yPos);
        }
    }
}

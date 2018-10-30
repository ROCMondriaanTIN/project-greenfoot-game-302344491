
import greenfoot.*;

public class Mover extends Actor {

    public int screenX;
    public int screenY;
    public double velocityX;
    public double velocityY;
    protected Camera camera;

    private int worldX;
    private int worldY;
    private boolean firstLocation = true;
    private boolean hasCamera;

    public void setCamera(Camera camera) {
        this.camera = camera;
        hasCamera = true;
    }

    public void removeCamera() {
        this.camera = null;
        hasCamera = false;
    }

    public void applyVelocity() {
        if (Math.abs(velocityX) < 0.01) {
            velocityX = 0;
        }
        if (Math.abs(velocityY) < 0.01) {
            velocityY = 0;
        }
        setLocation((int) (getX() + .5 + velocityX), (int) (getY() + .5 + velocityY));
    }

    @Override
    public void setLocation(int x, int y) {
        if (firstLocation == true) {
            firstLocation = false;
            screenX = x;
            screenY = y;
        }
        super.setLocation(screenX, screenY);
        this.worldX = x;
        this.worldY = y;
    }

    @Override
    public int getX() {
        return this.worldX;
    }

    @Override
    public int getY() {
        return this.worldY;
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

}

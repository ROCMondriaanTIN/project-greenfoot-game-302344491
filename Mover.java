
import greenfoot.*;

public class Mover extends Actor {

    public int screenX;
    public int screenY;
    public double velocityX;
    public double velocityY;
    protected Camera camera;

    private int worldX;
    private int worldY;
    private boolean firstLocation;
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
        setLocation((int)(getX() + .5 + velocityX), (int)(getY() + .5 + velocityY));
    }

    @Override
    public void setLocation(int x, int y) {
        if (firstLocation == true) {
            firstLocation = false;
            screenX = x;
            screenY = y;
        }
        if (hasCamera) {
            super.setLocation(screenX, screenY);
            this.worldX = x;
            this.worldY = y;
        } else {
            super.setLocation(x, x);
            this.worldX = this.screenX = x;
            this.worldY = this.screenY = y;
        }
    }

    @Override
    public int getX() {
        if (hasCamera) {
            return this.worldX;
        }
        return super.getX();
    }

    @Override
    public int getY() {
        if (hasCamera) {
            return this.worldY;
        }
        return super.getY();
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

}

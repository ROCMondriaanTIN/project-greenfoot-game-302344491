import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class MovingPlat extends Mover {

    private int walkRange;
    private int xMin;
    private int xMax;
    private boolean firstAct;
    private int speed;
    

    public MovingPlat() {
        super();
        setImage("stoneHalf.png");
        walkRange = 400;
        firstAct = true;
        speed = 1;
    }
    

    @Override
    public void act() {
        int x = getX();
        int y = getY();

        if (firstAct) {
            firstAct = false;
            xMin = x - walkRange / 2;
            xMax = x + walkRange / 2;
        }

        velocityX = speed;
        applyVelocity();
        if (getX() >= xMax) {
            speed *= -1;
            x = xMax;
        } else if (getX() <= xMin) {
            speed *= -1;
            x = xMin;
        }
    }
}

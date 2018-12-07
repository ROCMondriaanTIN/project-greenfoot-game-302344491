
import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Hero extends Mover {

    private final double gravity;
    private final double acc;
    private final double drag;
    private boolean inAir;
    private int x = 185;
    private int y = 493;
    public String actieveWereld="";
    Scorenbord scb;
    public String verzamel = "";
    public String activeWorld = "";
    private GreenfootImage walk1 = new GreenfootImage("p1_walk01.png");
    private GreenfootImage walk2 = new GreenfootImage("p1_walk02.png");
    private GreenfootImage walk3 = new GreenfootImage("p1_walk03.png");
    private GreenfootImage walk4 = new GreenfootImage("p1_walk04.png");
    private GreenfootImage walk5 = new GreenfootImage("p1_walk05.png");
    private GreenfootImage walk6 = new GreenfootImage("p1_walk06.png");
    private GreenfootImage walk7 = new GreenfootImage("p1_walk07.png");
    private GreenfootImage walk8 = new GreenfootImage("p1_walk08.png");
    private GreenfootImage walk9 = new GreenfootImage("p1_walk09.png");
    private GreenfootImage walk10 = new GreenfootImage("p1_walk10.png");
    private GreenfootImage walk11 = new GreenfootImage("p1_walk11.png");
    private int frame = 1;

    public Hero(String actieveWereld)  {
        super();
        this.actieveWereld=actieveWereld;
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage("p1.png");
    }

    @Override
    public void act() {
        handleInput();
        door();
        if(scb == null){
            scb = new Scorenbord();
            getWorld().addObject(scb, -10, -10);
        }
        if(activeWorld == ""){
            String activeWorld = "MyWorld";
        }
        addLetter();
        checkpoint();
        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        applyVelocity();
        for (Actor DodelijkeLava : getIntersectingObjects(DodelijkeLava.class)) {
            if (DodelijkeLava != null && DodelijkeLava instanceof DodelijkeLava) {
                setLocation(x, y);
            }
            break;
        }

        for (Actor enemy : getObjectsInRange(85, Enemy.class)) {
            if (enemy != null) {
                velocityX = -20;
                velocityY = -10;
                setImage("p1_hurt.png");

                //setLocation(x,y);
            }
        }
        

        /*if(getObjectsInRange(100,Enemy.class).size() == 0){
            setImage("p1.png");
        }*/
        for (Actor enemy : getIntersectingObjects(Highjump.class)) {
            if (enemy != null) {
                velocityY = -30;
                setLocation(getX() + 0, getY());
                setImage("p1_jump.png");
                return;
            }
        }
        
        for (Actor platform : getIntersectingObjects(MovingPlat.class)) {
            if (platform != null) {
                velocityY = -1.5;
                return;
            }
        }
    }
    public void door()
    {
        if( actieveWereld=="Level1")
        {
            for(Tile door:getIntersectingObjects(DoorTile.class))
            {
                if(door!=null)
                {
                    //if(coin==20)
                   // {
                    Greenfoot.setWorld(new MyWorld());    
                }
                     //}
            }
        }
        if( actieveWereld=="MyWorld")
        {
            for(Tile door:getIntersectingObjects(DoorTile.class))
            {
                if(door!=null)
                {
                    //if(coin==20)
                   // {
                    Greenfoot.setWorld(new Level1());    
                }
                     //}
            }
        }
}
    public String addLetter(){
        for(Letter verzamelLetter : getIntersectingObjects(Letter.class)){
            this.verzamel += verzamelLetter.letter;
            getWorld().removeObject(verzamelLetter);
            scb.voegLetterToe(verzamelLetter.letter);
        }
        return verzamel;
    }
    public String getPositie()
    {
    String a="X:"+getX()+" Y:"+getY();    
    return a;
    }
    public void checkpoint() {
        if (isTouching(Checkpoint.class)) {
            x = getX();
            y = getY();
        }
    }

    public void handleInput() {
        /*if (Greenfoot.isKeyDown("space")) {
            for (JumpTile tile : getIntersectingObjects(JumpTile.class)) {
                if (tile != null) {
                    inAir = false;
                    velocityY = -15;
                    setImage("p1_jump.png");
                } else {
                    inAir = true;
                }
            }
        }*/

        if (Greenfoot.isKeyDown("space")) {
            velocityY = -12;
            setImage("p1_jump.png");
        }

        if (Greenfoot.isKeyDown("a")) {
            velocityX = -2;
            animateLeft();
        } else if (Greenfoot.isKeyDown("d")) {
            velocityX = 2;
            animateRight();
        }
    }

    public void animateRight() {
        if (frame == 1) {
            setImage(walk1);
        } else if (frame == 2) {
            setImage(walk2);
        } else if (frame == 3) {
            setImage(walk3);
        } else if (frame == 4) {
            setImage(walk4);
        } else if (frame == 5) {
            setImage(walk5);
        } else if (frame == 6) {
            setImage(walk6);
        } else if (frame == 7) {
            setImage(walk7);
        } else if (frame == 8) {
            setImage(walk8);
        } else if (frame == 9) {
            setImage(walk9);
        } else if (frame == 10) {
            setImage(walk10);
        } else if (frame == 11) {
            setImage(walk11);
            frame = 1;
            return;
        }

        frame++;

    }

    public void animateLeft() {
        if (frame == 1) {
            setImage("p1_left_walk01.png");
        } else if (frame == 2) {
            setImage("p1_left_walk02.png");
        } else if (frame == 3) {
            setImage("p1_left_walk03.png");
        } else if (frame == 4) {
            setImage("p1_left_walk04.png");
        } else if (frame == 5) {
            setImage("p1_left_walk05.png");
        } else if (frame == 6) {
            setImage("p1_left_walk06.png");
        } else if (frame == 7) {
            setImage("p1_left_walk07.png");
        } else if (frame == 8) {
            setImage("p1_left_walk08.png");
        } else if (frame == 9) {
            setImage("p1_left_walk09.png");
        } else if (frame == 10) {
            setImage("p1_left_walk10.png");
        } else if (frame == 11) {
            setImage("p1_left_walk11.png");
            frame = 1;
            return;
        }

        frame++;

    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }

}

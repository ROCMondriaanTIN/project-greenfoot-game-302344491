import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        int [][] map = {
            {0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
        };
        
        Hero hero = new Hero();
        
        TileEngine te = new TileEngine(this, 60, 60, map);
        Camera camera = new Camera(te);
        camera.follow(hero);
        addObject(hero, 300, 200);
        addObject(camera, 0, 0);
        this.setActOrder( Camera.class, Hero.class);
    }
}

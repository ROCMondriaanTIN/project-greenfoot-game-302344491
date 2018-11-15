import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartScherm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScherm extends World
{

    /**
     * Constructor for objects of class StartScherm.
     * 
     */
    public StartScherm()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        StartKnop startKnop = new StartKnop();
        addObject(startKnop,500,400);
        Enemy enemy = new Enemy();
        addObject(enemy,601,437);
        enemy.setLocation(588,376);
        enemy.setLocation(586,338);
        removeObject(enemy);
    }
    
    
    
    /*public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        Greenfoot.setWorld(new MyWorld());
    }*/

    public void act()
    {
        
    }

}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CopyOfStartKnop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CopyOfStartKnop extends Actor
{
    /**
     * Act - do whatever the CopyOfStartKnop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        click();
    }    
     public void click()
    {
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new MyWorld());
        }
    }
}

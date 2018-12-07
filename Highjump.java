import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Highjump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Highjump extends Tile
{
    int frame= 2;
    /**
     * Act - do whatever the Highjump wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Highjump(String image, int height, int weight) 
    {
        super (image, weight, height);
    }  
    @Override
    public void act()
    {
    
    for(Actor hero:getIntersectingObjects(Hero.class))
    {
        if(frame == 1){
            setImage ("springboardDown.png");
            
        }
        
        else if(frame == 2){
            setImage ("springboardUp.png");
            
            frame = 1;
            
            return;
        }
        Greenfoot.delay(20);
        frame ++;
        Greenfoot.delay(20);
        
    }
    }
}
    


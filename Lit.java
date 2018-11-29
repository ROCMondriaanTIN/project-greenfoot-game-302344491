import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lit extends Tile
{
    int frame =0;
    
    public Lit(String image, int height, int weight) 
    {
        super (image, weight, height);
    }  
    /**
     * Act - do whatever the Lit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(frame == 1){
            setImage ("tochLit.png");
            
        }
        
        else if(frame == 2){
            setImage ("tochLit2.png");
            
            frame = 1;
            
            return;
        }
        
        frame ++;
        
    }    
}

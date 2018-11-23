import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Highjump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Highjump extends Tile
{
    int frame=1;
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
    
    
        if(frame == 1){
            setImage ("springboardDown.png");
        }
        if(frame == 2){
            setImage ("springboardDown.png");
        }
        if(frame == 3){
            setImage ("springboardDown.png");
        }
        if(frame == 4){
            setImage ("springboardUp.png");
        }
        if(frame == 5){
            setImage ("springboardUp.png");
        }
        else if(frame == 6){
            setImage ("springboardUp.png");
            frame = 1;
            return;
        }
        frame ++;
        
    }
    }
    


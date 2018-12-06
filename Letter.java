import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class letters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Letter extends Mover
{
    char letter;
    GreenfootImage img;
    boolean isOpscoreboard = false;
    public Letter(char letter, boolean b){
        this(letter);
        this.isOpscoreboard = b;

    }

    public Letter(char letter){
        this.letter = letter;
        setImage(letter + ".png");
    }

    // public Letter(char letter, String img){

    // }

    public void act()
    {
        if(!isOpscoreboard){
            applyVelocity();
        }else{
            
        }
    } 

    // public void letterEraf(){
    // LettersLatenZien();
    // }

    // public void LettersLatenZien(){
    // setImage("A.png");

    // }

}

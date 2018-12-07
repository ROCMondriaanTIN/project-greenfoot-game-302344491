import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Scorenbord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scorenbord extends Actor
{
    ArrayList<Letter>verzameldeLetters = new ArrayList<Letter>();
    public void voegLetterToe(char letter){
        //voegt een letter in de arraylist toe
        verzameldeLetters.add(new Letter(letter, true));
        laatlettersZien();
    }

    public void laatlettersZien(){
        //   System.out.println("show letters");
        //  Letter let = verzameldeLetters.get(0);
        for(int a = 0; a < verzameldeLetters.size(); a++){
            Letter lettertje = verzameldeLetters.get(a);
            getWorld().addObject(lettertje, 50+(a*67), 120);
        }
    }
}
/**
 * Write a description of class PLayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private int health;
    private int score;
    private int moves;
    
    public Player(String name)
    {
        this.name = name;
        health = 100;
        moves = 0;
        score = 0;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void increaseScore(int amount)
    {
        score = score + amount;
    }
}
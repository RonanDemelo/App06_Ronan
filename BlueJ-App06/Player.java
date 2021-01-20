
import java.util.ArrayList;
/**
 * Write a description of class PLayer here.
 *
 * Authors by Will Deeley, Ronan Demelo and James Pjetri
 * Version 2021.01.14
 */
public class Player
{
    private String name;
    public int energy;
    private int score;
    private int moves;
    private Room currentRoom;

    public ArrayList<Item> items;

    public Player(String name)
    {
        this.name = name;
        items = new ArrayList<Item>();
        energy = 100;
        moves = 0;
        score = 0;
    }

    public int getScore()
    {
        return score;
    }

    /** 
     * increases the players score
     */
    public void increaseScore(int amount)
    {
        score = score + amount;
    }

    /** 
     * counts the number of moves
     */
    public void move()
    {
        moves++;
        if(score > 0)
        {
            score--;
        }
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return score;
    }

    /** 
     * changes the player energy for the food item, and caps at 100
     */
    public void setEnergy(int addEnergy)
    {
        if (energy >= 0 && energy <= 90) 
        {
            energy = energy + addEnergy;
        }
        else 
        {
            energy = 100;
        }
    }

    /** 
     * adds items to the player inventory and increases the score accordingly
     */
    public void addItem(Item item)
    {
        items.add(item);
        score+= 10;
    }

    /** 
     * list of player items
     */
    public void Inventory()
    {
        System.out.println("You currently have:");
        for (Item items : items) 
        {
            System.out.println(items.name);
        }
    }
    
    /** 
     * remove items from the player inventory
     */
    public void removeItem(String name)
    {
        Item item = getItem(name);
        {
            if (item != null)
            {
                items.remove(0);
            }
        }
    }

    public String getItemName(String name)
    {
        for(Item item : items) 
        {
            if(item.getName().equals(name)) 
            {
                return item.getName();
            }
        }

        // if we get to this point no item with this name exists.
        return null;
    }
    
    private Item getItem(String name)
    {
        for(Item item : items) 
        {
            if(item.getName().equals(name)) 
            {
                return item;
            }
        }

        // if we get to this point no item with this name exists.
        return null;
    }

    /** 
     * reduces player energy for when they moce
     */
    public void useEnergy()
    {
        energy = energy - 5;
    }

    /** 
     * prints player stats
     */
    public void print()
    {
        System.out.println("\n Moves: " + moves + " " 
            + name + " Energy: " + energy 
            + " Score: " + score + "\n");
    }
}
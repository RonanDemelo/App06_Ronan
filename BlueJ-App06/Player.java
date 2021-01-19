
import java.util.ArrayList;
/**
 * Write a description of class PLayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private int energy;
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

    public void increaseScore(int amount)
    {
        score = score + amount;
    }

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

    public void addItem(Item item)
    {
        items.add(item);
        score+= 10;
    }

    public void Inventory()
    {
        System.out.println("You currently have:");
        for (Item items : items) 
        {
            System.out.println(items.name);
        }
    }

    public void removeItem(String name)
    {
        for (Item items : items) 
        {
            if (items.name == name)
            {
                items.remove(1);
            }
        }
    }

    public String getItem(String name)
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

    public void useEnergy()
    {
        energy = energy - 5;
    }

    public void print()
    {
        System.out.println("\n Moves: " + moves + " " 
            + name + " Energy: " + energy 
            + " Score: " + score + "\n");
    }
}
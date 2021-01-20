
/**
 * Enumeration class Items - write a description of the enum class here
 *
 * Authors by Will Deeley, Ronan Demelo and James Pjetri
 * Version 2021.01.14
 */
public class Item
{
    // GuardKey, Food, Shovel, Lockpick
    public String name;
    public int ID;
    public int amount;
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Item(String name, int amount)
    {
        this.name = name;
        this.amount = amount;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public String itemToString()
    {
        String nameString = name;
        return nameString;
    }
    
}

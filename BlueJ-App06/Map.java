
/**
 * Write a description of class Map here.
 *
 * Authors by Will Deeley, Ronan Demelo and James Pjetri
 * Version 2021.01.14
 */
public class Map
{
    private Room startRoom;
    
    public Map()
    {
        createRooms();
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room YourCell, Hallway1, Hallway2, Hallway3, Hallway4, Cell2, JanitorCloset, GuardRoom, Vent, MaintinaceRoom, Cafeteria, 
        Exit, Shower, Sewers, Courtyard, Outside;
      
        // create the rooms
        YourCell = new Room("in your cell", false);
        Cell2 = new Room("in the neighbour cell", false);
        Hallway1 = new Room("in the 1st hallway", true);
        Hallway2 = new Room("in the 2nd hallway", false);
        Hallway3 = new Room("in the 3rd hallway", false);
        Hallway4 = new Room("in the 4th hallway", false);
        GuardRoom = new Room("in the Guardroom", false);
        MaintinaceRoom = new Room("in the Maintinance room", false);
        JanitorCloset = new Room("in the Janitors Closet", true);
        Shower = new Room("in the Showers", false);
        Courtyard = new Room("in the Courtyard", true);
        Cafeteria = new Room("in the Cafeteria", false);
        Sewers = new Room("in the Sewers", true);
        Outside = new Room("outside", true);
        Exit = new Room("at the prison entrence", false);
        Vent = new Room("crawling through the vents", false);
        
        //item creation. includes the nmber of times the item can be picked up
        Item Screwdriver = new Item("Screwdriver", 1);
        MaintinaceRoom.additem(Screwdriver);
        
        Item Lockpick = new Item("Lockpick", 1);
        YourCell.additem(Lockpick);
        
        Item Food = new Item("Food", 1);
        Cafeteria.additem(Food);
        Food.amount = 3;
        
        Item Water = new Item("Water", 1);
        Cafeteria.additem(Water);
        
        Item Knife = new Item("Knife", 1);
        Cafeteria.additem(Knife);
        
        Item JanitorKey = new Item ("JanitorKey", 1);
        GuardRoom.additem(JanitorKey);
        
        Item Ladder = new Item ("Ladder", 1);
        JanitorCloset.additem(Ladder);
        
        Item Money = new Item ("Money", 1);
        GuardRoom.additem(Money);
        
        //room exit creation
        YourCell.setExit("south", Hallway1);
        
        Hallway1.setExit("north", YourCell);
        Hallway1.setExit("east", Hallway2);
        Hallway1.setExit("south", Cell2);
        
        Cell2.setExit("north", Hallway1);
        Cell2.setExit("south", Vent);
        
        Hallway2.setExit("north", JanitorCloset);
        Hallway2.setExit("east", Hallway3);
        Hallway2.setExit("west", Hallway1);
        
        GuardRoom.setExit("north", Hallway2);
        GuardRoom.setExit("west", Vent);
        
        Hallway3.setExit("north", Cafeteria);
        Hallway3.setExit("east", Hallway4);
        Hallway3.setExit("south", Shower);
        Hallway3.setExit("west", Hallway2);
        
        Hallway4.setExit("north", MaintinaceRoom);
        Hallway4.setExit("east", Exit);
        Hallway4.setExit("south", Courtyard);
        Hallway4.setExit("west", Hallway3);
        
        Shower.setExit("north", Hallway4);
        Shower.setExit("south", Sewers);
        
        JanitorCloset.setExit("south", Hallway2);
        
        Courtyard.setExit("north", Hallway4);
        Courtyard.setExit("east", Outside);
        
        Vent.setExit("north", Cell2);
        Vent.setExit("east", GuardRoom);
        
        Sewers.setExit("north", Shower);
        Sewers.setExit("east", Outside);
        
        Cafeteria.setExit("south", Hallway3);
        
        MaintinaceRoom.setExit("south", Hallway4);
        
        Exit.setExit("east", Outside);
        startRoom = YourCell;  // start game in your cell 
    }
    
    public Room getStart()
    {
        return startRoom;
    }
}


/**
 * Write a description of class Map here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
        Cell2 = new Room("in the nahours cell", false);
        Hallway1 = new Room("in the 1st hallway", false);
        Hallway2 = new Room("in the 2nd hallway", false);
        Hallway3 = new Room("in the 3rd hallway", false);
        Hallway4 = new Room("in the 4th hallway", false);
        GuardRoom = new Room("in the Guardroom", false);
        MaintinaceRoom = new Room("in the Maintinance room", true);
        JanitorCloset = new Room("in the Janitors Closet", false);
        Shower = new Room("in the Showers", false);
        Courtyard = new Room("in the Courtyard", true);
        Cafeteria = new Room("in the Cafeteria", false);
        Sewers = new Room("in the Sewers", true);
        Outside = new Room("outside", false);
        Exit = new Room("at the prison entrence", true);
        Vent = new Room("crawling through the vents", false);
        
        //item creation
        Item Screwdriver = new Item("Screwdriver");
        MaintinaceRoom.additem(Screwdriver);
        
        Item Food = new Item("Food");
        Cafeteria.additem(Food);
        Food.amount = 3;
        
        Item Water = new Item("Water");
        Cafeteria.additem(Water);
        
        Item Knife = new Item("Knife");
        Cafeteria.additem(Knife);
        
        Item JanitorKey = new Item ("JanitorKey");
        GuardRoom.additem(JanitorKey);
        
        Item Ladder = new Item ("Ladder");
        JanitorCloset.additem(Ladder);
        
        Item Money = new Item ("Money");
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
        Hallway2.setExit("south", GuardRoom);
        Hallway2.setExit("west", Hallway1);
        
        GuardRoom.setExit("north", Hallway2);
        GuardRoom.setExit("west", Vent);
        
        Hallway3.setExit("north", JanitorCloset);
        Hallway3.setExit("east", Hallway4);
        Hallway3.setExit("south", Shower);
        Hallway3.setExit("west", Hallway2);
        
        Hallway4.setExit("north", MaintinaceRoom);
        Hallway4.setExit("east", Exit);
        Hallway4.setExit("south", Courtyard);
        Hallway4.setExit("west", Hallway3);
        
        Shower.setExit("north", Hallway4);
        Shower.setExit("south", Sewers);
        
        JanitorCloset.setExit("south", Hallway3);
        
        Courtyard.setExit("north", Hallway4);
        
        Vent.setExit("north", Cell2);
        Vent.setExit("east", GuardRoom);
        
        startRoom = YourCell;  // start game in your cell 
    }
    
    public Room getStart()
    {
        return startRoom;
    }
}

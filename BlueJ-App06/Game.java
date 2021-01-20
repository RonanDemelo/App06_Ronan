/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Will Deeley, Ronan Demelo and James Pjetri
 * Version 2021.01.14
 */

public class Game 
{
    private Map map;
    private Player player;
    private Parser parser;
    private Room currentRoom;
    private Room nextRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        map = new Map();
        currentRoom = map.getStart();
        player = new Player("Player");
    }

    /**
     *  Main play routine.  Loops until end of play.
     *  added check for win/lose condition
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;

        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.energy == 0)
            {
                System.out.println("You have run out of energy and are caught by the guards. you lose");
                finished = true;
            }
            else if (currentRoom.getShortDescription() == "outside")
            {
                System.out.println("Congragulations, you have escaped. You win");
                finished = true;
            }
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
            System.out.println("I don't know what you mean...");
            break;

            case HELP:
            printHelp();
            break;

            case GO:
            goRoom(command);
            break;

            case SEARCH:
            searchRoom(command);
            break;

            case TAKE:
            takeItem();
            break;

            case ITEMS:
            listInventory();
            break;

            case USE:
            useItem(command);
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("You are in a Prison.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        player.useEnergy();

        // Try to leave current room.
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) 
        {
            System.out.println("There is no door!");
        }
        else {
            if (nextRoom.locked != true)
            {
                currentRoom = nextRoom;
                player.move();
                player.print();
                System.out.println(currentRoom.getLongDescription());
            }
            else
            {
                System.out.println("This door is locked");
            }
        }
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void searchRoom(Command command) 
    {
        Item item = currentRoom.getItem();
        System.out.println("Items found: " + item.getName());
    }

    /** 
     * Methed to allow players to take items and reduce the items in the world, making them finite
     */
    private void takeItem()
    {
        Item item = currentRoom.getItem();       
        if (item == null)
        {
            System.out.println("No items found in " + currentRoom);
        }
        else
        {
            if (item.amount != 0)
            {
                player.addItem(item);
                System.out.println(item.getName() + " taken.");
                item.amount = item.amount - 1;
            }
            else
            {
                System.out.println("You already have that item");
            }
        }
    }

    /** 
     * lists items player has currently taken
     */
    private void listInventory()
    {
        player.Inventory();
    }
    
    /** 
     * allows a player to use an item, and outputs accordingly
     */
    private void useItem(Command command)
    {
        if(!command.hasSecondWord()) 
        {
            // check for valid input
            System.out.println("use what?");
            return;
        }
        String userItem = command.getSecondWord(); 
        userItem.toLowerCase();
        String item = player.getItemName(userItem);
        
        if (item == "Lockpick")
        {
            unlockDoorYourCell("Lockpick");
        }
        else if (item == "Janitorkey")
        {
            unlockDoorJanitorCloset("Janitorkey");
        }
        else if (item == "Screwdriver")
        {
            unlockDoorSewers("Screwdriver");
        }
        else if (item == "Ladder")
        {
            unlockDoorOutside("Ladder");
        }
        else if (item == "Knife")
        {
            unlockDoorExit("Knife");
        }
        else if (userItem =="Food")
        {
            player.setEnergy(20);
        }
        else if (userItem =="Water")
        {
            player.setEnergy(10);
        }
        else
        {
            System.out.println("You do not have that item");
        }
    }
    
    /** 
     * uses lockpicks to open the player cell. removes the item from the player inventory
     */
    private void unlockDoorYourCell(String Item)
    {
        if (nextRoom.locked = true)
        {
            nextRoom.locked = false;
            player.removeItem(Item);
        }
    }
    
    /** 
     * uses the Janitor key to open the Janitor Closet. removes the item from the player inventory
     */
    private void unlockDoorJanitorCloset(String Item)
    {
        if (nextRoom.locked = true)
        {
            nextRoom.locked = false;
            player.removeItem(Item);
        }
    }
    
    /** 
     * uses the Screwdriver item to open the Sewers . removes the item from the player inventory
     */
    private void unlockDoorSewers(String Item)
    {
        if (nextRoom.locked = true)
        {
            nextRoom.locked = false;
            player.removeItem(Item);
        }
    }
    
    /** 
     * uses the Ladder item to open the outside room. removes the item from the player inventory
     */
    private void unlockDoorOutside(String Item)
    {
        if (nextRoom.locked = true)
        {
            nextRoom.locked = false;
            player.removeItem(Item);
        }
    }
    
    /** 
     * uses the knife item to open the exit room. removes the item from the player inventory
     */
    private void unlockDoorExit(String Item)
    {
        if (nextRoom.locked = true)
        {
            nextRoom.locked = false;
            player.removeItem(Item);
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

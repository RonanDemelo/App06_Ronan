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
 * Modified and extended by Your name
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

    private void takeItem()
    {
        Item item = currentRoom.getItem();       
        if (item == null)
        {
            System.out.println("No items found in " + currentRoom);
        }
        else
        {
            if (item.amount == 1)
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

    private void listInventory()
    {
        player.Inventory();
    }
    // current dosnt work VVVVV
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
        String item = player.getItem(userItem);
        
        if (item == "lockpick")
        {
            unlockDoor("lockpick");
        }
        else if (userItem =="food")
        {
            player.setEnergy(50);
        }
        else
        {
            System.out.println("not work");
        }
    }
    
    private void unlockDoor(String Item)
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

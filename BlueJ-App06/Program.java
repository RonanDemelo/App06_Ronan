
/**
 * This class creates an instance of the Game
 * class and then calls on its run method.
 *
 * @author Derek Peacock
 * @version 0.1
 * 
 * Modified and extended by Will Deeley, Ronan Demelo and James Pjetri
 * Version 2021.01.14
 */
public class Program
{
    private static Game game;

    /**
     * This class creates and runs an instance of
     * the Game class
     */
    public static void main()
    {
        game = new Game();
        game.play();
    }
}

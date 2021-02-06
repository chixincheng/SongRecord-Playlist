/**
 * An exception thrown in the Playlist class to indicate that the
 * Playlist is full and can not accept anymore songs
 * @author
 *      XinCheng Chi
 * Date:
 *  July 6,2020
 */
public class FullPlaylistException extends Exception
{
    /**
     * Default constructor for an FullPlaylistException that
     * passes a default string to the Exception class constructor.
     * Postcondition:
     *    The object is created and contains the default message.
     */
    public FullPlaylistException()
    {
        super("The Playlist is full");
    }
    /**
     * Second constructor for the FullPlaylistException that
     * passes a provided string to the Exception class constructor.
     * @param message
     *    the message that the object is to contain
     * Postcondition:
     *    The object is created and contains the provided message.
     */
    public FullPlaylistException(String message)
    {
        super(message);
    }
}

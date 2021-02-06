 /**
 * An exception thrown in the SongRecord class to indicate that
 * the value entered/given is not within the proper range.
 * @author
 *      XinCheng Chi
 * Date:
 *  July 6,2020
 */
public class NotValidEntryException extends Exception
{
    /**
     * Default constructor for an NotValidEntryException that
     * passes a default string to the Exception class constructor.
     * Postcondition:
     *    The object is created and contains the default message.
     */
    public NotValidEntryException()
    {
        super("Not a valid entry");
    }
    /**
     * Second constructor for the NotValidEntryException that
     * passes a provided string to the Exception class constructor.
     * @param message
     *    the message that the object is to contain
     * Postcondition:
     *    The object is created and contains the provided message.
     */
    public NotValidEntryException(String message)
    {
        super(message);
    }
}

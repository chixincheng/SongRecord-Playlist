import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *An SongRecord object which contains information about a particular
 *audio file, it have two member variables for the title and artist
 *which are strings, and two member variables for the song's length
 *in minutes and seconds which are ints.
 * 
 * @author
 *      XinCheng Chi
 * Date:
 *  July 6,2020
 */
public class SongRecord implements Cloneable
{
    
    
    private String title; //Title of the Song
    private String artist;//Name of the Artist
    private int minutes; //how many minutes do the song last
    private int seconds;// how many seconds do the song last
    
    //SongRecord[] songs;
    //int count=0;
    
    /**
     * Constructs an SongRecord object with the given parameter, title of the song,
     * name of the artist, lengths of the song
     * @param title
     *      The title of the song
     * @param artist
     *      The name of the artist
     * @param minutes
     *      The minutes of the song
     * @param seconds
     *       The seconds of the song
     * @throws NotValidEntryException 
     *      Indicates that the provided/given entry is not within a valid range
     */
    public SongRecord(String title,String artist,int minutes,int seconds) throws NotValidEntryException
    {
        this.title=title;//assign the value for title
        this.artist=artist;//assign the value for artist
        boolean set=true;//when its true, assign the value of minutes and seconds, if its false, it will be set to default
        try
        {
            if(minutes<0)//check if the input is valid
                throw new NotValidEntryException("Invalid song length");
            if(seconds<0||seconds>59)//check if the input is valid
                throw new NotValidEntryException("Invalid song length");
            if(minutes == 0 && seconds == 0)//check if the input is valid
                throw new NotValidEntryException("Invalid song length");
        }
        catch(NotValidEntryException e)
        {
            System.out.println(e);
            set=false;
        }
        if(set)//if the input is valid, set the value for minutes and seconds
        {
            this.minutes=minutes;
            this.seconds=seconds;
        }
    }
    /**
     * Accessor methods for getting title
     * @return 
     *      The title of the song
     */
    String getTitle()
    {
        return title;
    }
    /**
     * Mutator methods for setting title
     * @param til 
     *      Title for the song
     */
    public void setTitle(String til)
    {
        title=til;
    }
    /**
     * Accessor methods for getting artist
     * @return 
     *      Name of the artist
     */
    String getArtist()
    {
        return artist;
    }
    /**
     * Mutator methods for setting artist
     * @param arti
     *      Name of the artist
     */
    public void setArtist(String arti)
    {
        artist=arti;
    }
    /**
     * Accessor methods for getting seconds of the play time
     * @return 
     *      The length of songs in seconds
     */
    int getSeconds()
    {
        return seconds;
    }
    /**
     * Mutator methods for setting seconds of the play time
     * @param sec
     *      The length of songs in seconds
     * @throws NotValidEntryException 
     *      Indicates that the provided/given entry is not within a valid range
     */
    public void setSeconds(int sec) throws NotValidEntryException
    {
        boolean set=true;//will turn to false if input is not valid
        try
        {
            if(sec<0||sec>59)//check if input is valid
                throw new NotValidEntryException("Not a valid entry, should be between 0 and 59");
        }
        catch(NotValidEntryException e)
        {
            System.out.println(e);
            set=false;
        }
        if(set)//when input is valid,set the value using input
            seconds=sec;
    }
    /**
     * Accessor methods for getting minutes of the play time
     * @return 
     *      The length of songs in minutes
     */     
    int getMinutes()
    {
        return minutes;
    }
    /**
     * Mutator methods for setting minutes of the play time
     * @param min
     *      The length of songs in minutes
     * @throws NotValidEntryException 
     *      Indicates that the provided/given entry is not within a valid range
     */
    public void setMinutes(int min) throws NotValidEntryException
    {
        boolean set=true;//will turn to false if input is not valid
        try
        {
            if(min<0)//check if input is valid
                throw new NotValidEntryException("Not a valid entry, should be greater or equal to 0");
        }
        catch(NotValidEntryException e)
        {
            System.out.println(e);
            set=false;
        }
        if(set)//when input is valid, set the value using input
            minutes=min;
    }
    /**
     * Returns the String representation of this SongRecord object
     * @return
     *      The return value is the String representation of this
     *      SongRecord object.
     */
    public String toString()
    {
        String list="";
        if(seconds>=10)
            list=String.format("%-20s%-20s%-20s",title,artist,minutes+":"+seconds);
        else
            list=String.format("%-20s%-20s%-20s",title,artist,minutes+":0"+seconds);
        return list;
    }
    /**
     * Compares this SongRecord object to another object for equality.
     * @param obj
     *      An object to which this SongRecord object is being compared with
     * @return 
     *    A return value of true indicates that obj refers to a
     *    SongRecord object with the same elements as this SongRecord object
     *    Otherwise, the return value is false
     */
    public boolean equals(Object obj)
    {
        boolean ret=false;//will turn true if the input object is an SongRecord object
        if(obj instanceof SongRecord)//check if input object is an SongRecord object
        {
            SongRecord other = (SongRecord)obj;//cast the object to a SongRecord
            if(this.title.equals(other.title) && this.artist.equals(other.artist))//check if title and artist is the same
            {
                if(this.minutes == other.minutes && this.seconds == other.seconds)//check if the length is the same
                {
                    ret=true;
                }
            }
        }
        return ret;
    }
    /**
     * Generates a copy of this SongRecord object.
     * @return 
     *      The return value is a copy of this SongRecord object Subsequent changes
     *      to the copy will not affect the original, nor vice versa.
     * @throws java.lang.CloneNotSupportedException
     *      Indicate that an object could not or should not be cloned.
     */
    public Object clone() throws CloneNotSupportedException
    {
        SongRecord newSong = null;
        try 
        {
            //try to creat a SongRecord object with the same title, artist, and length
            newSong = new SongRecord(this.getTitle(),this.getArtist(),this.getMinutes(),this.getSeconds());
        } 
        catch (NotValidEntryException ex) 
        {
            Logger.getLogger(SongRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newSong;
    }
    
}

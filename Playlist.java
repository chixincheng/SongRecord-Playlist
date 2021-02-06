import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * An abstract data type meant to serve as a representation of a SongRecord Playlist,
 * containing up to 50 SongRecord objects and with methods to manipulate the Playlist
 * @author
 *      XinCheng Chi
 * Date:
 *  July 6,2020
 */
public class Playlist implements Cloneable
{
    /**
     * The maximum valid entry of songs
     */
    public static final int MAXIMUM= 51;
    //Invariant of the Playlist Class:
    private SongRecord[] songs=new SongRecord[51];//Array for storing songs
    private int numSongs;//Number of songs in this playlist
    private SongRecord empty;//Empty SongRecord to help initialize the empty array of SongRecord
    private String names;//Name of the Playlsit
    /**
     * Construct an instance of the Playlist class
     * Postcondition:
     * This Playlist has been initialized to an empty list of SongRecords.
     */
    public Playlist()
    {
        songs = new SongRecord[MAXIMUM];
        numSongs = 0;
    }
    /**
     * Construct an instance of the Playlist class with the given string as the name of the Playlist
     * @param name 
     *      The name of the Playlist
     * Postcondition:
     *      This Playlist has been initialized to an empty list of SongRecords.
     */
    public Playlist(String name)
    {
        songs = new SongRecord[MAXIMUM];
        numSongs = 0;
        names=name;
    }
    /**
     * Generate a copy of this Playlist
     * @return
     *      A copy of this Playlist
     * @throws java.lang.CloneNotSupportedException
     *      Indicate that an object could not or should not be cloned.
     */
    public Object clone() throws CloneNotSupportedException
    {
        Playlist newlist=new Playlist();
        for(int i=1;i<=numSongs;i++)
        {
            try 
            {
                newlist.addSong(this.getSong(i),i);//Try to clone the object
            }
            catch (NotValidEntryException ex) 
            {
                Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (FullPlaylistException ex)
            {
                Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newlist;//Return the new Playlist object
    }
    /**
     * Generate a copy of this Playlist using simpleAdd and simpleGetSong
     * @param name
     *      The name for the clone list
     * @return
     *      A copy of this Playlist
     * @throws java.lang.CloneNotSupportedException
     *      Indicate that an object could not or should not be cloned.
     */
    public Object extraClone(String name) throws CloneNotSupportedException//(extra credit)
    {
        Playlist newlist=new Playlist(name);
        for(int i=1;i<=numSongs;i++)
        {
            try 
            {
                newlist.simpleAdd(this.simpleGetSong(i),i);//Try to clone the object
            }
            catch (FullPlaylistException ex)
            {
                Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newlist;//Return the new Playlist object
    }
    
    /**
     * Compare this Playlist to another object for equality
     * @param obj
     *      An object to which this Playlist is being compared with
     * @return 
     *      A return value of true indicates that obj refers to a Playlist object with
     *      the same elements as this Playlist, Otherwise, return false.
     */
    public boolean equals(Object obj)
    {
        boolean ret=true;//set a default return boolean to true
        if(obj instanceof Playlist)//Check if the object is a Playlist
        {
            Playlist otherPlaylist=(Playlist) obj;// cast the object to a Playlist
            if(this.numSongs == otherPlaylist.numSongs)//check if the sizes are equal
            {
                for(int i=1;i<MAXIMUM;i++)//check every songs in the playlist
                {
                    if(this.songs[i] != null && otherPlaylist.songs[i] != null)//check if SongRecord exist at position i
                    {
                        if(this.songs[i].equals(otherPlaylist.songs[i]))//compare the SongRecord at position i
                        {
                            
                        }
                        else//SongRecord not equal so return false
                            ret=false;
                    }
                }
            }
            else// size not equal so return false
                ret=false;
        }
        else//obj is not a Playlist object so return false
            ret=false;
        return ret;
    }
    /**
     * Determines the number of SongRecords currently in this Playlsit
     * Preconditions:
     *      This Playlist object has been instantiated
     * @return 
     *      The number of SongRecords in this Playlist
     */
    public int size()
    {
        return numSongs;
    }
    
    /**
     * Add a new song to the Playlist
     * @param song
     *      the new song to be added to the Playlist
     * @param position 
     *      the position for where the song will be added
     * Precondition:
     *      This Playlist has been instantiated and song is within the valid range
     * Postcondition:
     *      The new SongRecord is now stored at the desired position in the Playlist.
     *      All SongRecords that were originally in positions greater than or equal 
     *      to position are moved back one position.
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range
     * @throws java.lang.CloneNotSupportedException
     *      Indicate that an object could not or should not be cloned.
     * @throws NotValidEntryException
     *      Indicates that the provided/given entry is not within a valid range
     * @throws FullPlaylistException
     *      Indicates that there is no more room inside of the Playlist
     *      to store the new SongRecord object.
     */
    public void addSong(SongRecord song, int position) throws FullPlaylistException, CloneNotSupportedException, NotValidEntryException
    {
        boolean print=true;//Will print the statement after a song is successfully added
        try
        {
            if((position<0||position>MAXIMUM))//check if given position is valid
                throw new IllegalArgumentException("The position is not within the valid range");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
        try
        {
            if(numSongs==MAXIMUM)//check if Playlist is full
                throw new FullPlaylistException("The Playlist is full");
        }
        catch(FullPlaylistException e)
        {
            System.out.println(e);
        }
        try
        {
            if(position<=numSongs+1)
            {
                moveBack(position);//if existing song's position is grater than the inserted position, move it back
                songs[position]=song;//add the song to the desired position
                numSongs++;
            }
            else
                throw new NotValidEntryException("Invalid position for adding the new song.");
        }
        catch(NotValidEntryException e)
        {
            System.out.println(e);
            print=false;
        }
        if(print)
            System.out.println("Sound Added: "+song.getTitle()+" By "+song.getArtist());
    }
    /**
     * Add a new song to the Playlist without printing
     * @param song
     *      the new song to be added to the Playlist
     * @param position 
     *      the position for where the song will be added
     * Precondition:
     *      This Playlist has been instantiated and song is within the valid range
     * Postcondition:
     *      The new SongRecord is now stored at the desired position in the Playlist.
     *      But this will not move back the SongRecords object that were originally 
     *      in positions greater than or equal to position
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range
     * @throws java.lang.CloneNotSupportedException
     *      Indicate that an object could not or should not be cloned.
     * @throws FullPlaylistException
     *      Indicates that there is no more room inside of the Playlist
     *      to store the new SongRecord object.
     */
    public void simpleAdd(SongRecord song, int position) throws FullPlaylistException, CloneNotSupportedException
    {
        if((position<0||position>MAXIMUM))//check is given position is valid
        {
            throw new IllegalArgumentException("The position is not within the valid range");
        }
        if(numSongs==MAXIMUM)//check is Playlist is full
            throw new FullPlaylistException("The Playlist is full");
        songs[position]=song;//add the song to the desired position
        numSongs++;
    }
    /**
     * Moveback the Playlist of songs when a new song is being added to a valid position
     * @param position 
     *      The position where the new song is being added
     */
    public void moveBack(int position)
    {
        if(numSongs>0)
        {
            for(int i=numSongs+1;i>0;i--)
            {
                if(i>position)
                {
                    songs[i]=songs[i-1];
                }
            }
        }
        else
            ;
    }
    /**
     * Remove the song at the given position
     * @param position 
     *      The position of where the song is being removed
     * Precondition:
     *      The Playlist object has been instantiated and the given position is valid
     * Postcondition:
     *      The SongRecord at the desired position has been removed, and all SongRecord
     *      originally at the position greater than the position being removed are moved 
     *      forward one position
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range
     *      Indicates that the given position is not valid, no SongRecord at the desired position
     */
    public void removeSong(int position)throws IllegalArgumentException
    {
        boolean run=true;//after caught an exception, will turn to false, and stop printing song being removed
        try
        {
            if((position<0||position>MAXIMUM))//check is given position is valid
            {
                throw new IllegalArgumentException("The position is not within the valid range");
            }
            if(songs[position]==null)//check is there exist a song at the given position
            {
                throw new IllegalArgumentException("There is no song at the provided position");
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
            run=false;
        }
        if(run)//check if there is a song at the valid position to be removed
        {
            moveUp(position);
            numSongs--;
            System.out.println("Song Removed at position:"+position);
        }
    }
    /**
     * Moveup the Playlist after a song is removed
     * @param position 
     *      The position where the song is being removed.
     */
    public void moveUp(int position)
    {
        for(int i=position;i<MAXIMUM;i++)
        {
            if(i==50)
            {
                songs[i]=null;
            }
            else
                songs[i]=songs[i+1];
        }
        songs[numSongs]=null;//last position for the pre-existing songs will be set to null
    }
    /**
     * Get the SongRecord at the given position in this Playlist and print it out
     * @param position
     *      The position of the SongRecord that we getting
     * @return 
     *      The SongRecord at the given position
     * Precondition:
     *      This Playlist object has been instantiated and have songs in it
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range
     */
    public SongRecord getSong(int position)
    {
        try
        {
            if((position<0||position>MAXIMUM))//check if given position is valid
                throw new IllegalArgumentException("The position is not within the valid range");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
        if(songs[position] != null)//check if there exist a song at the given position
        {
            System.out.println(String.format("%-20s%-20s%-20s%-20s","Song#","Title","Artist","Length"));
            System.out.println("------------------------------------------------------------------");
            System.out.println(String.format("%-20s%-20s",position+"",songs[position].toString()));
        }
        return songs[position];//return the song at the given position
    }  
     /**
     * Get the SongRecord at the given position in this Playlist without printing
     * @param position
     *      The position of the SongRecord that we getting
     * @return 
     *      The SongRecord at the given position
     * Precondition:
     *      This Playlist object has been instantiated and have songs in it
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range
     */
    public SongRecord simpleGetSong(int position)
    {
        try
        {
            if((position<0||position>MAXIMUM))//check if given position is valid
                throw new IllegalArgumentException("The position is not within the valid range");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
        return songs[position];//return the song at the given position
    }
    /**
     * Print a neatly formatted table of each SongRecord in the Playlist line by line with position number
     * Precondition:
     *      This Playlist object has been instantiated
     * Postcondition:
     *      A neatly formatted table of each SongRecord in the Playlist on its own line with position number
     */
    public void printAllSongs()
    {
        toString();
    }
    /**
     * Generates a new Playlist containing all SongRecords of the specified artist from the original Playlist
     * @param originalList
     *      The original Playlist
     * @param artist
     *      The name of the specified artist
     * @return
     *      A new Playlist containing all SongRecords of the specified artist from the original Playlist
     * Precondition:
     *       The Playlist referred to by originalList has been instantiated
     * @throws FullPlaylistException 
     *      Indicates that there is no more room inside of the Playlist
     *      to store the new SongRecord object.
     * @throws java.lang.CloneNotSupportedException
     *      Indicate that an object could not or should not be cloned.
     */
    public static Playlist getSongsByArtist(Playlist originalList, String artist)throws FullPlaylistException, CloneNotSupportedException
    {
        int retpos=1;//position for the return Playlist ret
        Playlist ret = new Playlist();//this is the Playlist this method will return
        for(int i=1;i<=originalList.size();i++)
        {
            if(originalList.simpleGetSong(i).getArtist().equals(artist))//check is the artist's name is the same
            {
                ret.simpleAdd(originalList.songs[i], retpos);//add to the return Playlist
                retpos++;
            }
        }
        ret.toString();
        return ret;//return a Playlist with songs of the specified artist only
    }
    /**
     * Gets the String representation of this Playlist object, which is a neatly formatted table of each
     * SongRecord in the Playlist on its own line with position number
     * @return 
     *      The string representation of this Playlist object.
     */
    public String toString()
    {
        System.out.println(String.format("%-20s%-20s%-20s%-20s","Song#","Title","Artist","Length"));
        System.out.println("------------------------------------------------------------------");
        
        if(numSongs==0)//check if there exist a song
        {
            return "";
        }
        for(int i=1;i<MAXIMUM;i++)
        {
            if(songs[i]!= null)//check if song exist at position i
            {
                System.out.println(String.format("%-20s%-20s",i+"",songs[i].toString()));
            }
        }
        return "";
    }
    /**
     * Set the name of Playlist
     * @param name 
     *      Name of Playlist
     */
    public void setName(String name)//(extra credit)
    {
        this.names=name;
    }
    /**
     * Get the name of Playlist
     * @return 
     *      Name of Playlist
     */
    public String getName()//(extra credit)
    {
        return names;
    }
    /**
     * Grab the position of the Playlist that the user want to change to
     * @param list
     *      List of the Playlist
     * @param name
     *      Name of the Playlist user want to change to
     * @return 
     *      The position of the Playlist the user want to change to
     */
    public int checkPos(Playlist[] list, String name)//(extra credit)
    {
        int listpos=0;
        for(int i=1;i<=list.length;i++)
        {
            if(list[i] != null)
            {
                if(list[i].getName().equals(name))
                {
                    listpos=i;
                    break;
                }
            }
        }
        return listpos;
    }
}

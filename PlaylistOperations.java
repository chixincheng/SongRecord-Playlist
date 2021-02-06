import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * An driver program used to test out the Playlist Abstract Data Type
 * written as part of this assignment.The user can use the commands below
 * to perform operations on Playlist objects.
 * Commands:
 *    (A)Add Song 
 *    (G)Get Song 
 *    (R)Remove Song
 *    (P)Print All Songs
 *    (B)Print Songs By Artist
 *    (S)Size
 *    (Q)Quit
 * @author
 *      XinCheng Chi
 * Date:
 *  July 6,2020
 */
public class PlaylistOperations 
{
    /**
     * The main operating method for this program.Data is obtained from the
     * user and is placed into Playlist which are next operated upon and the results
     * are displayed immediately after input is complete
     * @throws IOException
     *      Indicates an error reading in from the keyboard.
     * @throws FullPlaylistException
     *      Indicates that there is no more room inside of the Playlist
     *      to store the new SongRecord object.
     * @throws NotValidEntryException
     *      Indicates that the provided/given entry is not within a valid range
     * @throws CloneNotSupportedException 
     *      Indicate that an object could not or should not be cloned.
     */
    public static void main(String[]args) throws IOException, FullPlaylistException, NotValidEntryException, CloneNotSupportedException
    {
        Playlist currentlist = new Playlist("current");//Playlist reference for operations.
        
        int count=1;//Count for position and total Playlist  (extra credit)
        
        Playlist[] list = new Playlist[51];// A list of Playlist (extra credit)
        
        list[1] = currentlist;//save the default Playlist to position 1  (extra credit)
        
        
        char userSelection='M';//A character to hold the selected operation code.
                
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
         
        
        while(userSelection != 'Q')//Continues running until the user wishes to exit
        {
            System.out.println("A):"+"Add Song:");
            System.out.println("G):"+"Get Song:");
            System.out.println("R):"+"Remove Song:");
            System.out.println("P):"+"Print All Songs:");
            System.out.println("B):"+"Print Songs By Artist:");
            System.out.println("S):"+"Size:");
            System.out.println("N):"+"Create a new List and set it as current list:");
            System.out.println("V):"+"Change current list:");
            System.out.println("C):"+"Copy current list into new list:");
            System.out.println("E):"+"Compare a list with current list:");
            System.out.println("D):"+"Display all list:");
            System.out.println("Q):"+"Quit:");
            System.out.println(" ");
            System.out.print("Select a menu Option:");
            userSelection=Character.toUpperCase(input.readLine().charAt(0));
            
            switch(userSelection)//selects the operation
            {
                case'A':     //Add Song
                    SongRecord song=createSong();
                    if(song == null)
                        break;
                    else
                        currentlist.addSong(song,position());
                    break;
                case'G':    //Get Song
                    currentlist.getSong(getSongPos());
                    break;
                case'R':    //Remove Song
                    currentlist.removeSong(removeSong());
                    break;
                case'P'://Print All Songs
                    currentlist.printAllSongs();
                    break;
                case'B'://Print Songs By Artist
                    currentlist.getSongsByArtist(currentlist,getSongByArtistName());
                    break;
                case'S'://Size
                    System.out.println("There are "+currentlist.size()+" song(s) in the current playlist");
                    break;
                case'N'://(extra credit)
                    count++;//increase as new Playlist is created
                    
                    currentlist = newList();//change the current list
                    
                    list[count] = currentlist;//save the current list
                    
                    break;
                case'V'://(extra credit)
                    String name=listName();//get the name of the Playlist from user
                    
                    int pos = currentlist.checkPos(list,name);//check the position of the Playlist
                    
                    currentlist=list[pos];//change the Playlist
                    
                    break;
                case'C'://(extra credit)
                    Playlist copy = newList();//create a new list with user input
                    
                    count++;//increase as new Playlist is created
                    
                    name = copy.getName();//name of the copied list
                    
                    copy = (Playlist)currentlist.extraClone(name);//copy the song of currentlist to the list just created
                    
                    list[count]=copy;//save the new Playlist to list of Playlist
                    
                    break;
                case'E'://(extra credit)
                    name=listName();//get the name of the Playlist from user
                    
                    pos = currentlist.checkPos(list,name);//check the position of the Playlist
                    
                    boolean same=false;//default to false
                    
                    if(currentlist.equals(list[pos]))//check if the Playlists are same
                    {
                        same=true;
                    }
                    if(same)
                        System.out.println("The given Playlist is the same as the current Playlist");
                    if(!same)
                        System.out.println("The Playlists are not the same");
                    break;
                case'D'://(extra credit)
                    displayAllList(list);//display all the Playlist
                    
                    break;
            }
        }
    }
    /**
     * Creates a SongRecord object based on user input
     * @return
     *      The SongRecord object created by user input
     * @throws IOException
     *      Indicates an error reading in from the keyboard.
     * @throws NotValidEntryException 
     *      Indicates that the provided/given entry is not within a valid range
     */
    public static SongRecord createSong() throws IOException, NotValidEntryException
    {    
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        
        System.out.print("Enter the song title:");
        String title=input.readLine();//Title of song enter by user
        
        System.out.print("Enter the song artist:");
        String artist=input.readLine();//Name of artist enter by user
        
        System.out.print("Enter the song length(minutes):");
        int minutes=Integer.parseInt(input.readLine());//Minutes of song enter by user
        
        System.out.print("Enter the song length(seconds):");
        int seconds=Integer.parseInt(input.readLine());//Seconds of song enter by user
        
        SongRecord song=new SongRecord(title,artist,minutes,seconds);//Create a SongRecord object based on user input
        
        if(song.getMinutes()==0 && song.getSeconds() == 00)//By default, the construct will set the value of int to 0 if value is not assigned
        {
            song=null;
        }
        return song;
    }
    /**
     * Ask user for a position
     * @return
     *      The position user enters
     * @throws IOException 
     *      Indicates an error reading in from the keyboard.
     */
    public static int position() throws IOException
    {
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        
        System.out.print("Enter the position:");
        int position=Integer.parseInt(input.readLine());//Position enter by user
        
        return position;
    }
    /**
     * Ask user for a position to get song
     * @return
     *      The position enter by user
     * @throws IOException 
     *      Indicates an error reading in from the keyboard.
     */
    public static int getSongPos() throws IOException
    {
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        
        System.out.print("Enter the position:");
        int pos=Integer.parseInt(input.readLine());//Position enter by user
        
        return pos;
    }
    /**
     * Ask user for a position to remove song
     * @return
     *      The position enter by user
     * @throws IOException 
     *      Indicates an error reading in from the keyboard.
     */
    public static int removeSong()throws IOException
    {
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        
        System.out.print("Enter the position:");
        int pos=Integer.parseInt(input.readLine());//Position enter by user
//        
        return pos;
    }
    /**
     * Ask user for the name of the artist for which they want to get song
     * @return
     *      The name of the artist enter by user
     * @throws IOException 
     *      Indicates an error reading in from the keyboard.
     */
    public static String getSongByArtistName() throws IOException
    {
        
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        
        System.out.print("Enter the  artist:");
        String artist=input.readLine();//Name of artist enter by user
        
        return artist;
    }
    /**
     * Create a new Playlist with the user inputing the name of the Playlist
     * @return
     *      The new Playlist created with the user input as name
     * @throws IOException 
     *      Indicates an error reading in from the keyboard.
     */
    public static Playlist newList() throws IOException//(extra credit)
    {
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        
        System.out.print("Enter the  Playlist name:");
        String name=input.readLine();//Name of Playlist enter by user
        
        Playlist ret=new Playlist(name);//create new Playlist with userinput

        return ret;
    }
    /**
     * Return the name of the Playlist that the user want to change to
     * @return
     *      The name of the Playlist that the user want to change to
     * @throws IOException 
     *      Indicates an error reading in from the keyboard.
     */
    public static String listName() throws IOException//(extra credit)
    {
        String ret="";
        
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        System.out.print("Enter the  Playlist name:");
        ret=input.readLine();//Name of Playlist enter by user
        
        return ret;
    }
    /**
     * Display the name of all the Playlist
     * @param list 
     *      The array of Playlist that will be displayed
     */
    public static void displayAllList(Playlist[] list)//(extra credit)
    {
        System.out.println(String.format("%-20s%-20s","Playlist#","Name"));
        System.out.println("------------------------------------------------------");
        for(int i=1;i<list.length;i++)
        {
            if(list[i] != null)//check if list exist at position i
                System.out.println(String.format("%-20s%-20s",i+"",list[i].getName()));
        }
    }
}

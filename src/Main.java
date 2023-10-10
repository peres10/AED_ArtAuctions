import artauctions.ArtAuctionsSystem;

import java.io.*;
import java.util.Scanner;

/**
 * @author Alexandre Peres 61615
 */
public class Main {

    private static final String DATA_FILE = "artAuctions.dat";

    /**
     * Commands used in the system
     */
    private enum Command {
        ADDUSER, ADDARTIST, REMOVEUSER, ADDWORK, INFOUSER, INFOARTIST, INFOWORK,
        CREATEAUCTION, ADDWORKAUCTION, BID, CLOSEAUCTION, LISTAUCTIONWORKS, LISTARTISTWORKS,
        LISTBIDSWORK, LISTWORKSBYVALUE, QUIT, UNKNOWN
    }

    /**
     * System messages and formats when successful
     */
    private enum Msg {
        ADDED_USER( "Registo de utilizador executado." ),
        ADDED_ARTIST( "Registo de artista executado." ),
        REMOVED_USER( "Remocao de utilizador executada." ),
        ADDED_WORK( "Registo de obra executado." ),
        USER_INFO( "%s %s %d %s\n" ),
        ARTIST_INFO( "%s %s %s %d %s\n" ),
        WORK_INFO( "%d %s %d %d %s %s\n" ),
        AUCTION_CREATED( "Registo de leilao executado." ),
        ADDED_WORK_AUCTION( "Obra adicionada ao leilao." ),
        ACCEPTED_BID( "Proposta aceite." ),
        CLOSED_AUCTION( "Leilao encerrado." ),
        SOLD_WORK_AUCTION( "%d %s %s %s %d\n" ),
        REMAIN_WORK_AUCTION( "%d %s sem propostas de venda.\n" ),
        LIST_WORKS_AUCTION( "%d %s %d %d %s %s\n" ),
        LIST_WORKS_ARTIST( "%d %s %d %d\n" ),
        LIST_BIDS( "%s %s %d\n" ),
        LIST_WORKS( "%d %s %d %d %s %s\n" ),
        QUIT( "Obrigado. Ate a proxima." )
        ;

        private final String msg;

        Msg( String msg ){
            this.msg = msg;
        }

        String getMsg(){
            return msg;
        }
    }

    /**
     * System messages and formats when an error happens
     */
    private enum ErrorMsg{
        ;
        private final String errorMsg;

        ErrorMsg( String errorMsg ){
            this.errorMsg = errorMsg;
        }

        String getMsg(){
            return errorMsg;
        }
    }


    /**
     *
     * @param args
     */
    public static void main( String[] args ){
        commands();
    }

    /**
     * Command interpreter
     */
    public static void commands(){
        ArtAuctionsSystem data = load();
        Scanner in = new Scanner( System.in );
        Command com = null;

        while( com != Command.QUIT ){
            com = getCommand( in );
            switch ( com ){
                case ADDUSER :
                    break;
                case ADDARTIST :
                    break;
                case REMOVEUSER :
                    break;
                case ADDWORK :
                    break;
                case INFOUSER:
                    break;
                case INFOARTIST :
                    break;
                case INFOWORK :
                    break;
                case CREATEAUCTION :
                    break;
                case ADDWORKAUCTION :
                    break;
                case BID :
                    break;
                case CLOSEAUCTION :
                    break;
                case LISTAUCTIONWORKS :
                    break;
                case LISTARTISTWORKS :
                    break;
                case LISTBIDSWORK :
                    break;
                case LISTWORKSBYVALUE :
                    break;
                case QUIT :
                    System.out.println(Msg.QUIT.getMsg());
                    break;
                default :
                    break;
            }
            System.out.println();
        }
        in.close();
        save(data);
    }

    /**
     * Receives a command and verifies if it is a command of the program
     *
     * @param in - Input Scanner
     * @return - a command
     */
    private static Command getCommand ( Scanner in ){
        try{
            return Command.valueOf( in.next().toUpperCase() );
        } catch( IllegalArgumentException e ){
            return Command.UNKNOWN;
        }
    }

    /**
     * Loads the program from an external file if the file exists
     *
     * @return
     */
    private static ArtAuctionsSystem load(){
        ArtAuctionsSystem data;
        try{
            ObjectInputStream file = new ObjectInputStream(
                    new FileInputStream(DATA_FILE)
            );
            data = (ArtAuctionsSystem) file.readObject();
            file.close();
        } catch (IOException | ClassNotFoundException e){
            data = new ArtAuctionsSystemClass();
        }
        return data;
    }

    /**
     * Saves the program to an external file, creates a file if there
     * is not one already
     *
     * @param data
     */
    private static void save( ArtAuctionsSystem data ){
        try{
            ObjectOutputStream file = new ObjectOutputStream(
                    new FileOutputStream( DATA_FILE )
            );
            file.writeObject(data);
            file.flush();
            file.close();
        } catch ( IOException e ){
            System.out.println( "Error saving: "+e.getMessage() );
        }
    }
}

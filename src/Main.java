import artauctions.ArtAuctionsSystem;
import artauctions.exceptions.*;

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
        WORK_INFO( "%s %s %d %d %s %s\n" ),
        AUCTION_CREATED( "Registo de leilao executado." ),
        ADDED_WORK_AUCTION( "Obra adicionada ao leilao." ),
        ACCEPTED_BID( "Proposta aceite." ),
        CLOSED_AUCTION( "Leilao encerrado." ),
        SOLD_WORK_AUCTION( "%s %s %s %s %d\n" ),
        REMAIN_WORK_AUCTION( "%s %s sem propostas de venda.\n" ),
        LIST_WORKS_AUCTION( "%s %s %d %d %s %s\n" ),
        LIST_WORKS_ARTIST( "%s %s %d %d\n" ),
        LIST_BIDS( "%s %s %d\n" ),
        LIST_WORKS( "%s %s %d %d %s %s\n" ),
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
        UNDERAGE_USER( "Idade inferior a 18 anos." ),
        USER_ALREADY_EXISTS( "Utilizador existente." ),
        USER_NOT_EXISTS( "Utilizador inexistente." ),
        WORK_ALREADY_EXISTS( "Obra existente." ),
        ARTIST_NOT_EXISTS( "Artista inexistente." ),
        WORK_NOT_EXISTS( "Obra inexistente." ),
        AUCTION_ALREADY_EXISTS( "Leilao existente." ),
        AUCTION_NOT_EXISTS( "Leilao inexistente." ),
        AUCTION_EMPTY( "Leilao sem obras." ),
        ARTIST_WITHOUT_WORKS( "Artista sem obras." ),
        WORK_NOT_IN_AUCTION( "Obra inexistente no leilao." ),
        WORK_WITHOUT_BIDS( "Obra sem propostas." ),
        AUCTION_WITHOUT_ANY_SELL( "Nao existem obras ja vendidas em leilao." )
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
                    addUser( in, data );
                    break;
                case ADDARTIST :
                    addArtist();
                    break;
                case REMOVEUSER :
                    removeUser();
                    break;
                case ADDWORK :
                    addWork();
                    break;
                case INFOUSER:
                    infoUser();
                    break;
                case INFOARTIST :
                    infoArtist();
                    break;
                case INFOWORK :
                    infoWork();
                    break;
                case CREATEAUCTION :
                    createAuction();
                    break;
                case ADDWORKAUCTION :
                    addWorkAuction();
                    break;
                case BID :
                    bid();
                    break;
                case CLOSEAUCTION :
                    closeAuction();
                    break;
                case LISTAUCTIONWORKS :
                    listAuctionWorks();
                    break;
                case LISTARTISTWORKS :
                    listArtistWorks();
                    break;
                case LISTBIDSWORK :
                    listBidsWork();
                    break;
                case LISTWORKSBYVALUE :
                    listWorksByValue();
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
     * Adds a new user (regular user) to the system
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void addUser( Scanner in, ArtAuctionsSystem data ){
        String login = in.next();
        int age = in.nextInt();
        in.nextLine();

        try{
            data.addUser();
            System.out.println( Msg.ADDED_USER.getMsg() );
        } catch( UnderageUserException e ){
            System.out.println( ErrorMsg.UNDERAGE_USER.getMsg() );
        } catch( UserAlreadyExistsException e ){
            System.out.println( ErrorMsg.USER_ALREADY_EXISTS.getMsg() );
        }
    }

    private static void addArtist(){

    }

    private static void removeUser(){

    }

    private static void addWork(){

    }

    private static void infoUser(){

    }

    private static void infoArtist(){

    }

    private static void infoWork(){

    }

    private static void createAuction(){

    }

    private static void addWorkAuction(){

    }

    private static void bid(){

    }

    private static void closeAuction(){

    }

    private static void listAuctionWorks(){

    }

    private static void listArtistWorks(){

    }

    private static void listBidsWork(){

    }

    private static void listWorksByValue(){

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

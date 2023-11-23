import artauctions.*;
import artauctions.exceptions.*;
import dataStructures.Entry;
import dataStructures.Iterator;

import java.io.*;
import java.util.Scanner;

/**
 * Main class
 *
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
        LIST_WORKS_OF_ARTIST ( "%s %s %d %d\n" ),
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
        NO_WORK_HAS_BEEN_ACTIONED( "Nao existem obras ja vendidas em leilao." ),
        USER_HAS_ACTIVE_BIDS( "Utilizador com propostas submetidas." ),
        ARTIST_HAS_WORKS_AUCTIONED( "Artista com obras em leilao." ),
        VALUE_UNDER_MINIMUM_BID( "Valor proposto abaixo do valor minimo." )
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
        Scanner in = new Scanner( System.in );
        ArtAuctionsSystem data = load();

        commands(in, data);
        in.close();
    }

    /**
     * Command interpreter
     */
    public static void commands( Scanner in, ArtAuctionsSystem data){
        Command com = null;

        while( com != Command.QUIT ){
            com = getCommand( in );
            System.out.println();
            switch ( com ){
                case ADDUSER :
                    addUser( in, data );
                    break;
                case ADDARTIST :
                    addArtist( in, data );
                    break;
                case REMOVEUSER :
                    removeUser( in, data );
                    break;
                case ADDWORK :
                    addWork( in, data );
                    break;
                case INFOUSER:
                    infoUser( in, data );
                    break;
                case INFOARTIST :
                    infoArtist( in, data );
                    break;
                case INFOWORK :
                    infoWork( in, data );
                    break;
                case CREATEAUCTION :
                    createAuction( in, data );
                    break;
                case ADDWORKAUCTION :
                    addWorkAuction( in, data );
                    break;
                case BID :
                    bid( in, data );
                    break;
                case CLOSEAUCTION :
                    closeAuction( in, data );
                    break;
                case LISTAUCTIONWORKS :
                    listAuctionWorks( in, data );
                    break;
                case LISTARTISTWORKS :
                    listArtistWorks( in, data );
                    break;
                case LISTBIDSWORK :
                    listBidsWork( in, data );
                    break;
                case LISTWORKSBYVALUE :
                    listWorksByValue( data );
                    break;
                case QUIT:
                    quitProgram( data );
                    return;
                default :
                    break;
            }
            System.out.println();
        }
        //quitProgram(data);
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
     * Outputs the quit message and saves the database
     *
     * @param data - ArtAuctionsSystem that will be saved
     */
    private static void quitProgram(ArtAuctionsSystem data){
        System.out.println(Msg.QUIT.getMsg());
        save(data);
    }

    /**
     * Adds a new user (regular user) to the system
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void addUser( Scanner in, ArtAuctionsSystem data ){
        String login = in.next();
        String name = in.nextLine().trim();
        int age = in.nextInt();
        String email = in.next();
        in.nextLine();

        try{
            data.addUser( login, name, age, email );
            System.out.println( Msg.ADDED_USER.getMsg() );
        } catch( UnderageUserException e ){
            System.out.println( ErrorMsg.UNDERAGE_USER.getMsg() );
        } catch( UserAlreadyExistsException e ){
            System.out.println( ErrorMsg.USER_ALREADY_EXISTS.getMsg() );
        }
    }

    /**
     * Adds a new Artist to the system
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void addArtist( Scanner in, ArtAuctionsSystem data ){
        String login = in.next();
        String name = in.nextLine().trim();
        String artisticName = in.nextLine().trim();
        int age = in.nextInt();
        String email = in.next();
        in.nextLine();

        try {
            data.addArtist( login, name, age, email, artisticName );
            System.out.println( Msg.ADDED_ARTIST.getMsg() );
        } catch (UnderageUserException e) {
            System.out.println( ErrorMsg.UNDERAGE_USER.getMsg() );
        } catch (UserAlreadyExistsException e) {
            System.out.println( ErrorMsg.USER_ALREADY_EXISTS.getMsg() );
        }
    }

    /**
     * Removes a User from the system
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void removeUser( Scanner in, ArtAuctionsSystem data ){
        String login = in.next();
        in.nextLine();

        try {
            data.removeUser( login );
            System.out.println( Msg.REMOVED_USER.getMsg() );
        } catch (UserNotExistsException e) {
            System.out.println( ErrorMsg.USER_NOT_EXISTS.getMsg() );
        } catch (UserHasActiveBidsException e) {
            System.out.println( ErrorMsg.USER_HAS_ACTIVE_BIDS.getMsg() );
        } catch (UserHasWorksAuctionedException e) {
            System.out.println( ErrorMsg.ARTIST_HAS_WORKS_AUCTIONED.getMsg() );
        }
    }

    /**
     * Adds a new work of art in the system
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void addWork( Scanner in, ArtAuctionsSystem data ){
        String idWork = in.next();
        String creatorLogin = in.next();
        int year = in.nextInt();
        String name = in.nextLine().trim();

        try {
            data.addWork(idWork, creatorLogin, year, name);
            System.out.println( Msg.ADDED_WORK.getMsg() );
        } catch (WorkAlreadyExistsException e) {
            System.out.println( ErrorMsg.WORK_ALREADY_EXISTS.getMsg() );
        } catch (UserNotExistsException e) {
            System.out.println( ErrorMsg.USER_NOT_EXISTS.getMsg() );
        } catch (ArtistNotExistsException e) {
            System.out.println( ErrorMsg.ARTIST_NOT_EXISTS.getMsg() );
        }
    }

    /**
     * Gets the information of a Regular User
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void infoUser( Scanner in, ArtAuctionsSystem data ){
        String login = in.next();
        in.nextLine();

        try{
            User user = data.infoUser( login );
            System.out.printf( Msg.USER_INFO.getMsg(), user.getLogin(), user.getName(), user.getAge(),
                    user.getEmail() );
        } catch (UserNotExistsException e) {
            System.out.println( ErrorMsg.USER_NOT_EXISTS.getMsg() );
        }
    }

    /**
     * Gets the information of an Artist
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void infoArtist( Scanner in, ArtAuctionsSystem data ){
        String login = in.next();
        in.nextLine();

        try{
            Artist user = data.infoArtist( login );
            System.out.printf( Msg.ARTIST_INFO.getMsg(), user.getLogin(), user.getName(), user.getArtisticName(),
                    user.getAge(), user.getEmail() );
        } catch (UserNotExistsException e) {
            System.out.println( ErrorMsg.USER_NOT_EXISTS.getMsg() );
        } catch (ArtistNotExistsException e) {
            System.out.println( ErrorMsg.ARTIST_NOT_EXISTS.getMsg() );
        }
    }

    /**
     * Gets the information of a Work of art
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void infoWork( Scanner in, ArtAuctionsSystem data ){
        String idWork = in.next();
        in.nextLine();

        try{
            Work work = data.infoWork( idWork );
            System.out.printf( Msg.WORK_INFO.getMsg(), work.getId(), work.getName(), work.getYear(),
                    work.getHighestSaleValue(), work.getCreatorLogin(), work.getCreatorName() );
        } catch (WorkNotExistsException e) {
            System.out.println( ErrorMsg.WORK_NOT_EXISTS.getMsg() );
        }
    }

    /**
     * Creates an Auction
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void createAuction( Scanner in, ArtAuctionsSystem data ){
        String idAuction = in.next();
        in.nextLine();

        try{
            data.createAuction( idAuction );
            System.out.println( Msg.AUCTION_CREATED.getMsg() );
        } catch (AuctionAlreadyExistsException e) {
            System.out.println( ErrorMsg.AUCTION_ALREADY_EXISTS.getMsg() );
        }
    }

    /**
     * Adds a work of art in an auction
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void addWorkAuction( Scanner in, ArtAuctionsSystem data ){
       String idAuction = in.next();
       String idWork = in.next();
       int minValue = in.nextInt();
       in.nextLine();

       try {
           data.addWorkAuction( idAuction, idWork, minValue );
           System.out.println( Msg.ADDED_WORK_AUCTION.getMsg() );
       } catch (AuctionNotExistsException e) {
           System.out.println( ErrorMsg.AUCTION_NOT_EXISTS.getMsg() );
       } catch (WorkNotExistsException e) {
           System.out.println( ErrorMsg.WORK_NOT_EXISTS.getMsg() );
       }
    }

    /**
     * Submites a buying bid in a Work on an Auction
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void bid( Scanner in, ArtAuctionsSystem data ){
        String idAuction = in.next();
        String idWork = in.next();
        String login = in.next();
        int value = in.nextInt();
        in.nextLine();

        try{
            data.bid( idAuction, idWork, login, value );
            System.out.println( Msg.ACCEPTED_BID.getMsg() );
        } catch (UserNotExistsException e) {
            System.out.println( ErrorMsg.USER_NOT_EXISTS.getMsg() );
        } catch (AuctionNotExistsException e) {
            System.out.println( ErrorMsg.AUCTION_NOT_EXISTS.getMsg() );
        } catch (WorkNotInAuctionException e) {
            System.out.println( ErrorMsg.WORK_NOT_IN_AUCTION.getMsg() );
        } catch (BidValueUnderMinValueException e) {
            System.out.println( ErrorMsg.VALUE_UNDER_MINIMUM_BID.getMsg() );
        }
    }

    /**
     * Closes an Auction and outputs the information of all works (sold and not sold) in that auction
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void closeAuction( Scanner in, ArtAuctionsSystem data ){
        String idAuction = in.next();
        in.nextLine();

        try{
            Iterator<WorkInAuction> it = data.closeAuction( idAuction );
            WorkInAuction workInAuction;
            Work work;

            System.out.println(Msg.CLOSED_AUCTION.getMsg());
            while(it.hasNext()){
                workInAuction = it.next();
                work = workInAuction.getWork();
                if(workInAuction.getIfWasSold())
                    System.out.printf(Msg.SOLD_WORK_AUCTION.getMsg(),
                            work.getId(), work.getName(),
                            workInAuction.getLoginBuyer(), workInAuction.getNameBuyer(),
                            workInAuction.getSaleValue());
                else
                    System.out.printf(Msg.REMAIN_WORK_AUCTION.getMsg()
                            ,work.getId(), work.getName() );
            }
        } catch (AuctionNotExistsException e) {
            System.out.println( ErrorMsg.AUCTION_NOT_EXISTS.getMsg() );
        }
    }

    /**
     * Lists all works in an Auction
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void listAuctionWorks( Scanner in, ArtAuctionsSystem data ){
        String idAuction = in.next();
        in.nextLine();

        try{
            Iterator<WorkInAuction> it = data.listAuctionWorks( idAuction );
            WorkInAuction workInAuction;
            Work work;

            while(it.hasNext()){
                workInAuction = it.next();
                work = workInAuction.getWork();
                System.out.printf(Msg.LIST_WORKS_AUCTION.getMsg(),
                        work.getId(), work.getName(), work.getYear(), work.getHighestSaleValue(),
                        work.getCreatorLogin(), work.getCreatorName() );
            }
        } catch (AuctionNotExistsException e) {
            System.out.println( ErrorMsg.AUCTION_NOT_EXISTS.getMsg() );
        } catch (AuctionEmptyException e) {
            System.out.println( ErrorMsg.AUCTION_EMPTY.getMsg() );
        }
    }

    /**
     * Lists all works from an artist
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void listArtistWorks( Scanner in, ArtAuctionsSystem data ){
        String login = in.next();
        in.nextLine();

        try{
            Iterator<Entry<String ,Work>> it = data.listArtistWorks(login);
            Work work;
            while(it.hasNext()){
                work = it.next().getValue();
                System.out.printf(Msg.LIST_WORKS_OF_ARTIST.getMsg(), work.getId(), work.getName(), work.getYear(),
                            work.getHighestSaleValue() );
            }
        } catch (UserNotExistsException e) {
            System.out.println( ErrorMsg.USER_NOT_EXISTS.getMsg() );
        } catch (ArtistNotExistsException e) {
            System.out.println( ErrorMsg.ARTIST_NOT_EXISTS.getMsg() );
        } catch (ArtistWithoutWorksException e) {
            System.out.println( ErrorMsg.ARTIST_WITHOUT_WORKS.getMsg() );
        }
    }

    /**
     * Lists all bids of a work in an auction
     *
     * @param in - Input Scanner
     * @param data - ArtAuctions data
     */
    private static void listBidsWork( Scanner in, ArtAuctionsSystem data ){
        String idAuction = in.next();
        String idWork = in.next();

        try{
            Iterator<Bid> it = data.listBidsWork( idAuction, idWork );
            Bid bid;
            while (it.hasNext()){
                bid = it.next();
                System.out.printf( Msg.LIST_BIDS.getMsg() , bid.userLogin(), bid.userName(), bid.getValue());
            }
        } catch (AuctionNotExistsException e) {
            System.out.println( ErrorMsg.AUCTION_NOT_EXISTS.getMsg() );
        }catch (WorkNotInAuctionException e) {
            System.out.println( ErrorMsg.WORK_NOT_IN_AUCTION.getMsg() );
        } catch (WorkWithoutBidsException e) {
            System.out.println( ErrorMsg.WORK_WITHOUT_BIDS.getMsg() );
        }
    }

    /**
     * Lists all works that have already been sold, ordered by selling value
     *
     * @param data - ArtAuctions data
     */
    private static void listWorksByValue( ArtAuctionsSystem data ){
        try{
            Iterator<Entry<Work,Work>> it = data.listWorksByValue();
            Work work;
            while (it.hasNext()){
                work = it.next().getValue();
                System.out.printf( Msg.LIST_WORKS.getMsg(), work.getId(), work.getName(), work.getYear() ,work.getHighestSaleValue(),
                        work.getCreatorLogin(), work.getCreatorName());
            }
        } catch (NoWorkHasBeenActionedException e) {
            System.out.println( ErrorMsg.NO_WORK_HAS_BEEN_ACTIONED.getMsg() );
        }
    }


    /**
     * Loads the program from an external file if the file exists
     *
     * @return data
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

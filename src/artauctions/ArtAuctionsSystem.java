package artauctions;

import artauctions.exceptions.*;
import dataStructures.Entry;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * ArtAuctions System interface
 *
 * @author Alexandre Peres 61615
 */
public interface ArtAuctionsSystem extends Serializable {

    /**
     * Adds a regular User to the system
     *
     * @param login - user login
     * @param name - user login
     * @param age - user age
     * @param email - user email
     * @throws UnderageUserException - if age < 18
     * @throws UserAlreadyExistsException - if login already exists
     */
    void addUser( String login, String name, int age, String email )
            throws UnderageUserException, UserAlreadyExistsException;

    /**
     * Adds an Artist to the system
     *
     * @param login - artist login
     * @param name - artist login
     * @param age - artist age
     * @param email - artist name
     * @param artisticName - artist artistic name
     * @throws UnderageUserException - if age < 18
     * @throws UserAlreadyExistsException - if login already exists
     */
    void addArtist( String login, String name, int age, String email, String artisticName )
            throws UnderageUserException, UserAlreadyExistsException;

    /**
     * Removes an User from the system
     *
     * @param login - user's login
     * @throws UserNotExistsException - if login doesn't exist
     * @throws UserHasActiveBidsException - if user has any active bids in any auction
     * @throws UserHasWorksAuctionedException - if user is an artists and has any of his works auctioned
     */
    void removeUser( String login )
            throws UserNotExistsException, UserHasActiveBidsException, UserHasWorksAuctionedException;

    /**
     * Adds work to the system
     *
     * @param idWork - id of the Work
     * @param loginCreator - login of the creator of the Work
     * @param year - year of the Work
     * @param name - name of the Work
     * @throws WorkAlreadyExistsException - if idWork already exists
     * @throws UserNotExistsException - if there is no User with loginCreator
     * @throws ArtistNotExistsException - if there is no Artist with loginCreator
     */
    void addWork( String idWork, String loginCreator, int year, String name )
            throws WorkAlreadyExistsException, UserNotExistsException, ArtistNotExistsException;

    /**
     * Gets the information of a User in the system
     *
     * @param login - login of a user
     * @return - a User object of the user searched
     * @throws UserNotExistsException - if there is no user with login
     */
    User infoUser( String login )
            throws UserNotExistsException;

    /**
     * Gets the information of an Artist in the system
     *
     * @param login  login of an artist
     * @return - a Artist object of the artist searched
     * @throws UserNotExistsException - if there is no user with login
     * @throws ArtistNotExistsException - if there is no artist with login
     */
    Artist infoArtist( String login )
            throws UserNotExistsException, ArtistNotExistsException;

    /**
     * Gets the information of a Work in the system
     *
     * @param idWork - id of a work
     * @return - a Work object of the work searched
     * @throws WorkNotExistsException - if there is no work in the system with the id
     */
     Work infoWork( String idWork )
            throws WorkNotExistsException;

    /**
     * Creates an auction in the system
     *
     * @param idAuction - id of that auction
     * @throws AuctionAlreadyExistsException - if idAuction already exists
     */
    void createAuction( String idAuction )
            throws AuctionAlreadyExistsException;

    /**
     * Adds a work to an auction
     *
     * @param idAuction - id of the auction
     * @param idWork - id of the work
     * @param minValue - minimum value of that auction
     * @throws AuctionNotExistsException - if idAuction does not exist
     * @throws WorkNotExistsException - if idWork does not exist
     */
    void addWorkAuction( String idAuction, String idWork, int minValue )
            throws AuctionNotExistsException, WorkNotExistsException;

    /**
     * Bids on an art work in an auction
     *
     * @param idAuction - id of the auction
     * @param idWork - id of the work
     * @param login - login of the user who is bidding
     * @param value - value of the bid
     * @throws AuctionNotExistsException - if idAuction does not exist
     * @throws WorkNotInAuctionException - if idWork does not exist or does not exist in the auction
     * @throws UserNotExistsException - if login does not exist
     * @throws BidValueUnderMinValueException - if value < minValue of the auction of the work
     */
    void bid( String idAuction, String idWork, String login, int value )
            throws AuctionNotExistsException, WorkNotInAuctionException, UserNotExistsException, BidValueUnderMinValueException;

    /**
     * Closes an auction and returns an iterator of all the works in the auction
     *
     * @param idAuction - id of the auction
     * @return - iterator of all the works in the auction
     * @throws AuctionNotExistsException - if idAuction does not exist
     */
    Iterator<WorkInAuction> closeAuction( String idAuction )
            throws AuctionNotExistsException;

    /**
     * Returns an iterator of all WorkInAuction of a specific Auction
     *
     * @param idAuction - ID of the auction
     * @return - Iterator of WorkInAuction with all WorkInAuction of a specific Auction
     * @throws AuctionNotExistsException - if there is no Auction with idAuction
     * @throws AuctionEmptyException - if the auction has no Works in it
     */
    Iterator<WorkInAuction> listAuctionWorks( String idAuction )
            throws AuctionNotExistsException, AuctionEmptyException;

    /**
     * Returns an iterator of Work's from a specific artist
     *
     * @param login - login of the artist
     * @return - iterator of all works from a Artist
     * @throws UserNotExistsException - if there is no user with login
     * @throws ArtistNotExistsException - if there is no artist with login
     * @throws ArtistWithoutWorksException - if thee artist has no works
     */
    Iterator<Entry<String , Work>> listArtistWorks( String login )
            throws UserNotExistsException, ArtistNotExistsException, ArtistWithoutWorksException;

    /**
     * Returns an iterator of all bids on a work in a specific autcion
     *
     * @param idAuction - id of the auction
     * @param idWork - id of the work
     * @return - Iterator of Bid with all bids on a work in a auction
     * @throws AuctionNotExistsException - if idAuction does not exist
     * @throws WorkNotInAuctionException - if idWork does not exist or does not exist in the auction
     * @throws WorkWithoutBidsException - if the work in the auction has not bids yet
     */
    Iterator<Bid> listBidsWork( String idAuction, String idWork )
            throws AuctionNotExistsException, WorkNotInAuctionException, WorkWithoutBidsException;

    /**
     * Returns an iterator of all works ordered by their highest sale value, and ordered by name
     *
     * @return - iterator o Work with works ordered by value and name
     * @throws NoWorkHasBeenActionedException - if no Work has been sold yet
     */
    Iterator<Entry<Work,Work>> listWorksByValue( )
            throws NoWorkHasBeenActionedException;
}

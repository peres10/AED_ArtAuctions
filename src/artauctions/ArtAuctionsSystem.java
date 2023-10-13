package artauctions;

import artauctions.exceptions.*;
import java.io.Serializable;

/**
 * ArtAuctions System interface
 *
 * @author Alexandre Peres 61615
 */
public interface ArtAuctionsSystem extends Serializable {

    /**
     * Adds a RegularUser to the system
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
     * @return - a RegularUser object of the user searched
     * @throws UserNotExistsException - if there is no user with login
     */
    RegularUser infoUser( String login )
            throws UserNotExistsException;

    /**
     * Gets the information of an Artist in the system
     *
     * @param login  login of an artist
     * @return - a Artist object of the artist searched
     * @throws UserNotExistsException - if there is no user with login
     * @throws ArtistNotExistsException - if there is not artist with login
     */
    public Artist infoArtist( String login )
            throws UserNotExistsException, ArtistNotExistsException;

    /**
     * Gets the information of a Work in the system
     *
     * @param idWork - id of a work
     * @return - a Work object of the work searched
     * @throws WorkNotExistsException - if there is no work in the system with the id
     */
    public Work infoWork( String idWork )
            throws WorkNotExistsException;

    void createAuction( )
            throws AuctionAlreadyExistsException;

    void addWorkAuction( )
            throws AuctionNotExistsException, WorkNotExistsException;

    void bid( )
            throws AuctionNotExistsException, WorkNotExistsException, UserNotExistsException;

    void closeAuction( )
            throws AuctionNotExistsException;

    void listAuctionWorks( )
            throws AuctionNotExistsException, AuctionEmptyException;

    void listArtistWorks( )
            throws UserNotExistsException, ArtistNotExistsException, ArtistWithoutWorksException;

    void listBidsWork( )
            throws AuctionNotExistsException, WorkNotInAuctionException, WorkWithoutBidsException;

    void listWorksByValue( )
            throws AuctionWithoutAnySellException;
}

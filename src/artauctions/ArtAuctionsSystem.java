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
     * @param name - user name
     * @param age - user age
     * @param email - user email
     * @throws UnderageUserException - if age < 18
     * @throws UserAlreadyExistsException - if login already exists
     */
    void addUser( String login, String name, int age, String email )
            throws UnderageUserException, UserAlreadyExistsException;

    void addArtist( )
            throws UnderageUserException, UserAlreadyExistsException;

    void removeUser( )
            throws UserNotExistsException;

    void addWork( )
            throws WorkAlreadyExistsException, UserNotExistsException, ArtistNotExistsException;

    RegularUser infoUser( String login )
            throws UserNotExistsException;

    void infoArtist( )
            throws UserNotExistsException, ArtistNotExistsException;

    void infoWork( )
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

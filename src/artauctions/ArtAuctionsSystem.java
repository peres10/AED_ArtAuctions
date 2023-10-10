package artauctions;

import artauctions.exceptions.*;

import java.io.Serializable;

public interface ArtAuctionsSystem extends Serializable {

    void addUser() throws UnderageUserException, UserAlreadyExistsException;

    void addArtist( ) throws UnderageUserException, UserAlreadyExistsException;

    void removeUser( ) throws UserNotExistsException;

    void addWork( ) throws WorkAlreadyExistsException, UserNotExistsException, ArtistNotExistsException;

    void infoUser( ) throws UserNotExistsException;

    void infoArtist( ) throws UserNotExistsException, ArtistNotExistsException;

    void infoWork( ) throws WorkNotExistsException;

    void createAuction( ) throws AuctionAlreadyExistsException;

    void addWorkAuction( ) throws AuctionNotExistsException, WorkNotExistsException;

    void bid( ) throws AuctionNotExistsException, WorkNotExistsException, UserNotExistsException;

    void closeAuction( ) throws AuctionNotExistsException;

    void listAuctionWorks( ) throws AuctionNotExistsException, AuctionEmptyException;

    void listArtistWorks( ) throws UserNotExistsException, ArtistNotExistsException, ArtistWithoutWorksException;

    void listBidsWork( ) throws AuctionNotExistsException, WorkNotInAuctionException, WorkWithoutBidsException;

    void listWorksByValue( ) throws AuctionWithoutAnySellException;
}

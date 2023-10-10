package artauctions;

import artauctions.exceptions.*;

public class ArtAuctionsSystemClass implements  ArtAuctionsSystem{

    public ArtAuctionsSystemClass(){

    }
    @Override
    public void addUser() throws UnderageUserException, UserAlreadyExistsException {

    }

    @Override
    public void addArtist() throws UnderageUserException, UserAlreadyExistsException {

    }

    @Override
    public void removeUser() throws UserNotExistsException {

    }

    @Override
    public void addWork() throws WorkAlreadyExistsException, UserNotExistsException, ArtistNotExistsException {

    }

    @Override
    public void infoUser() throws UserNotExistsException {

    }

    @Override
    public void infoArtist() throws UserNotExistsException, ArtistNotExistsException {

    }

    @Override
    public void infoWork() throws WorkNotExistsException {

    }

    @Override
    public void createAuction() throws AuctionAlreadyExistsException {

    }

    @Override
    public void addWorkAuction() throws AuctionNotExistsException, WorkNotExistsException {

    }

    @Override
    public void bid() throws AuctionNotExistsException, WorkNotExistsException, UserNotExistsException {

    }

    @Override
    public void closeAuction() throws AuctionNotExistsException {

    }

    @Override
    public void listAuctionWorks() throws AuctionNotExistsException, AuctionEmptyException {

    }

    @Override
    public void listArtistWorks() throws UserNotExistsException, ArtistNotExistsException, ArtistWithoutWorksException {

    }

    @Override
    public void listBidsWork() throws AuctionNotExistsException, WorkNotInAuctionException, WorkWithoutBidsException {

    }

    @Override
    public void listWorksByValue() throws AuctionWithoutAnySellException {

    }
}

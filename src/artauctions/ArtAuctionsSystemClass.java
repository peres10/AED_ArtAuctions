package artauctions;

import artauctions.exceptions.*;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * ArtAuctions System class
 *
 * @author Alexandre Peres 61615
 */
public class ArtAuctionsSystemClass implements  ArtAuctionsSystem{


    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * List containing every User in the system
     */
    List<User> users;
    /**
     * List containing every Auction in the system
     */
    List<Auction> auctions;
    /**
     * List containing every Work in the system
     */
    List<Work> works;


    /**
     * ArtAuctionsSystemClass constructor
     */
    public ArtAuctionsSystemClass(){
        users = new DoubleList<>();
        auctions = new DoubleList<>();
        works = new DoubleList<>();
    }

    @Override
    public void addUser( String login, String name, int age, String email ) throws UnderageUserException, UserAlreadyExistsException {
        if( age < 18 ) throw new UnderageUserException();
        if( searchUser(login) != null ) throw new UserAlreadyExistsException();

        users.addLast( new RegularUserClass( login, name, age, email));
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
    public RegularUser infoUser( String login ) throws UserNotExistsException {
        RegularUser user = (RegularUser) searchUser( login );
        if( user == null ) throw new UserNotExistsException();

        return user;
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


    private User searchUser(String login){
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getLogin().equalsIgnoreCase(login))
                return user;
        }
        return null;
    }
}

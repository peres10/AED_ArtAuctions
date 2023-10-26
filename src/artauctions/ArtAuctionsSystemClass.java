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
    private List<User> users;
    /**
     * List containing every Auction in the system
     */
    private List<Auction> auctions;
    /**
     * List containing every Work in the system
     */
    private List<Work> works;
    /**
     * Minimum age for a user to be registered
     */
    private static final int MINIMUM_AGE = 18;
    /**
     * ArtAuctionsSystemClass constructor
     */
    public ArtAuctionsSystemClass(){
        users = new DoubleList<>();
        auctions = new DoubleList<>();
        works = new DoubleList<>();
    }

    @Override
    public void addUser( String login, String name, int age, String email )
            throws UnderageUserException, UserAlreadyExistsException {
        if( age < MINIMUM_AGE )
            throw new UnderageUserException();

        if( searchUser(login) != null )
            throw new UserAlreadyExistsException();

        users.addLast( new UserClass( login, name, age, email ));
    }

    @Override
    public void addArtist( String login, String name, int age, String email, String artisticName )
            throws UnderageUserException, UserAlreadyExistsException {
        if( age < MINIMUM_AGE )
            throw new UnderageUserException();

        if( searchUser(login) != null )
            throw new UserAlreadyExistsException();

        users.addLast( new ArtistClass( login, name, age, email, artisticName ));
    }

    @Override
    public void removeUser( String login )
            throws UserNotExistsException, UserHasActiveBidsException, UserHasWorksAuctionedException {
        User user = searchUser(login);

        if ( user == null )
            throw new UserNotExistsException();

        if ( user.getNumOfActiveBids() > 0 )
            throw new UserHasActiveBidsException();

        if ( user instanceof Artist && ((Artist)user).getNumberOfAuctionWorks() > 0)
            throw new UserHasWorksAuctionedException();

        if (user instanceof Artist) {
            Artist artist = (Artist) user;
            removeAllWorksFromAnArtist( artist );
        }

        users.remove( user );
    }

    @Override
    public void addWork( String idWork, String loginCreator, int year, String name )
            throws WorkAlreadyExistsException, UserNotExistsException, ArtistNotExistsException {
        if( searchWork(idWork) != null)
            throw new WorkAlreadyExistsException();

        User userCreator = searchUser( loginCreator );
        if ( userCreator == null)
            throw new UserNotExistsException();

        if ( !(userCreator instanceof Artist) )
            throw new ArtistNotExistsException();

        ArtistPrivate artist = (ArtistPrivate) userCreator;

        Work newWork = new WorkClass( idWork, artist, year, name );

        artist.addWork( newWork );
        works.addLast( newWork );
    }

    @Override
    public User infoUser( String login )
            throws UserNotExistsException {
        User user = searchUser( login );

        if( user == null )
            throw new UserNotExistsException();

        return user;
    }

    @Override
    public Artist infoArtist( String login )
            throws UserNotExistsException, ArtistNotExistsException {
        User user = searchUser( login );
        if( user == null )
            throw new UserNotExistsException();

        if( !(user  instanceof Artist) )
            throw new ArtistNotExistsException();

        Artist artist = (Artist)user;

        return artist;
    }


    @Override
    public Work infoWork( String idWork )
            throws WorkNotExistsException {
        Work work = searchWork( idWork );
        if( work == null )
            throw new WorkNotExistsException();

        return work;
    }

    @Override
    public void createAuction( String idAuction )
            throws AuctionAlreadyExistsException {
        Auction auction = searchAuction( idAuction );
        if( auction != null )
            throw new AuctionAlreadyExistsException();

        auctions.addLast( new AuctionClass(idAuction) );
    }

    @Override
    public void addWorkAuction( String idAuction, String idWork, int minValue )
            throws AuctionNotExistsException, WorkNotExistsException {
        AuctionPrivate auction = (AuctionPrivate)searchAuction( idAuction );
        if( auction == null)
            throw new AuctionNotExistsException();

        WorkPrivate work = (WorkPrivate) searchWork( idWork );
        if( work == null )
            throw new WorkNotExistsException();

        auction.addWorkAuction( work, minValue );
    }

    @Override
    public void bid( String idAuction, String idWork, String login, int value )
            throws AuctionNotExistsException, WorkNotInAuctionException, UserNotExistsException, BidValueUnderMinValueException {
        User user = searchUser( login );
        if( user == null )
            throw new UserNotExistsException();

        Auction auction = searchAuction( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        Work work = searchWork( idWork );
        if( work == null )
            throw new WorkNotInAuctionException();

        //WorkNotInAuctionException e BidValueUnderMinValueException thrown a por outras funções
        ((AuctionPrivate)auction).bid( work, user, value );
    }

    @Override
    public Iterator<WorkInAuction> closeAuction( String idAuction )
            throws AuctionNotExistsException {
        Auction auction = searchAuction( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        return auction.closeAuction();
    }

    @Override
    public Iterator<WorkInAuction> listAuctionWorks( String idAuction )
            throws AuctionNotExistsException, AuctionEmptyException {
        Auction auction = searchAuction( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        if( auction.emptyAuction() )
            throw new AuctionEmptyException();

        return auction.worksInAuctionIterator();
    }

    @Override
    public void listArtistWorks()
            throws UserNotExistsException, ArtistNotExistsException, ArtistWithoutWorksException {

    }

    @Override
    public Iterator<Bid> listBidsWork( String idAuction, String idWork )
            throws AuctionNotExistsException, WorkNotInAuctionException, WorkWithoutBidsException {
        Auction auction = searchAuction( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        Work work = searchWork( idWork );
        if( work == null )
            throw new WorkNotInAuctionException();

        return auction.listBidsWork( work );

    }

    @Override
    public void listWorksByValue()
            throws AuctionWithoutAnySellException {

    }


    /**
     * Searches for a user in the List of users and returns it if exists
     *
     * @param login - login of a User
     * @return - if a User exists returns it, if not returns null
     */
    private User searchUser( String login ){
        Iterator<User> it = users.iterator();
        User user;
        while(it.hasNext()){
            user = it.next();
            if(user.getLogin().equalsIgnoreCase(login))
                return user;
        }
        return null;
    }

    /**
     * Searches for a work in the List of works and returns it if exists
     *
     * @param id - id of Work
     * @return - if a Work exists returns it, if not returns null
     */
    private Work searchWork( String id ){
        Iterator<Work> it = works.iterator();
        Work work;
        while(it.hasNext()){
            work = it.next();
            if(work.getId().equalsIgnoreCase(id))
                return work;
        }
        return null;
    }

    private Auction searchAuction( String id ){
        Iterator<Auction> it = auctions.iterator();
        Auction auction;
        while(it.hasNext()){
            auction = it.next();
            if(auction.getId().equalsIgnoreCase(id))
                return auction;
        }
        return null;
    }

    /**
     * Removes all works that were made by a specific user
     *
     * @param creator - Artist object, that must be valid, that may have works made by him
     */
    private void removeAllWorksFromAnArtist( Artist creator ){
        Iterator<Work> it = creator.worksIterator();
        Work work;
        while(it.hasNext()){
            work = it.next();
            works.remove(work);
        }
    }
}

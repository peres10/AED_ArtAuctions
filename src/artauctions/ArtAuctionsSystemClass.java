package artauctions;

import artauctions.exceptions.*;
import dataStructures.*;


/**
 * ArtAuctions System class
 *
 * @author Alexandre Peres 61615
 */
public class ArtAuctionsSystemClass implements  ArtAuctionsSystem{

    /**
     * Serial Version UID of the Class
     */
    private static final long serialVersionUID = 0L;
    /**
     * Dictionary containing every User in the system
     */
    private final Dictionary<String,User> users;
    /**
     * Dictionary containing every Auction in the system
     */
    private final Dictionary<String,Auction> auctions;
    /**
     * Dictionary containing every Work in the system
     */
    private final Dictionary<String,Work> works;
    /**
     * Ordered dictionary of all works ever sold ordered by their highest
     * sell value, and if the sell value is the same they are ordered by their name
     */
    private final OrderedDictionary<Work,Work> worksSoldOrderedByValue;
    /**
     * Minimum age for a user to be registered
     */
    private static final int MINIMUM_AGE = 18;
    /**
     * ArtAuctionsSystemClass constructor
     */
    public ArtAuctionsSystemClass(){
        users = new SepChainHashTable<>();
        auctions = new SepChainHashTable<>();
        works = new SepChainHashTable<>();
        worksSoldOrderedByValue = new AVLTree<>();
    }

    @Override
    public void addUser( String login, String name, int age, String email )
            throws UnderageUserException, UserAlreadyExistsException {
        if( age < MINIMUM_AGE )
            throw new UnderageUserException();

        if( users.find (login ) != null )
            throw new UserAlreadyExistsException();

        users.insert( login , new UserClass( login, name, age, email ) );
    }

    @Override
    public void addArtist( String login, String name, int age, String email, String artisticName )
            throws UnderageUserException, UserAlreadyExistsException {
        if( age < MINIMUM_AGE )
            throw new UnderageUserException();

        if( users.find( login ) != null )
            throw new UserAlreadyExistsException();

        users.insert( login, new ArtistClass( login, name, age, email, artisticName) );
    }

    @Override
    public void removeUser( String login )
            throws UserNotExistsException, UserHasActiveBidsException, UserHasWorksAuctionedException {
        User user = users.find( login );

        if ( user == null )
            throw new UserNotExistsException();

        if ( user.getNumOfActiveBids() > 0 )
            throw new UserHasActiveBidsException();

        if ( user instanceof Artist && ((Artist) user).getNumberOfAuctionWorks() > 0)
            throw new UserHasWorksAuctionedException();

        if (user instanceof Artist) {
            removeAllWorksFromAnArtist( (Artist)user );
        }

        users.remove( login );
    }

    @Override
    public void addWork( String idWork, String loginCreator, int year, String name )
            throws WorkAlreadyExistsException, UserNotExistsException, ArtistNotExistsException {
        if( works.find(idWork) != null)
            throw new WorkAlreadyExistsException();

        User userCreator = users.find( loginCreator );
        if ( userCreator == null )
            throw new UserNotExistsException();

        if ( !(userCreator instanceof Artist) )
            throw new ArtistNotExistsException();

        ArtistPrivate artist = (ArtistPrivate) userCreator;

        Work newWork = new WorkClass( idWork, artist, year, name );

        artist.addWork( newWork );
        works.insert( idWork,newWork );
    }

    @Override
    public User infoUser( String login )
            throws UserNotExistsException {
        User user = users.find( login );

        if( user == null )
            throw new UserNotExistsException();

        return user;
    }

    @Override
    public Artist infoArtist( String login )
            throws UserNotExistsException, ArtistNotExistsException {
        User user = users.find( login );
        if( user == null )
            throw new UserNotExistsException();

        if( !(user  instanceof Artist) )
            throw new ArtistNotExistsException();

        Artist artist = (Artist) user;

        return artist;
    }


    @Override
    public Work infoWork( String idWork )
            throws WorkNotExistsException {
        Work work = works.find( idWork );
        if( work == null )
            throw new WorkNotExistsException();

        return work;
    }

    @Override
    public void createAuction( String idAuction )
            throws AuctionAlreadyExistsException {
        Auction auction = auctions.find( idAuction );
        if( auction != null )
            throw new AuctionAlreadyExistsException();

        auctions.insert( idAuction, new AuctionClass(idAuction) );
    }

    @Override
    public void addWorkAuction( String idAuction, String idWork, int minValue )
            throws AuctionNotExistsException, WorkNotExistsException {
        AuctionPrivate auction = (AuctionPrivate) auctions.find( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        WorkPrivate work = (WorkPrivate) works.find( idWork );
        if( work == null )
            throw new WorkNotExistsException();

        auction.addWorkAuction( work, minValue );
    }

    @Override
    public void bid( String idAuction, String idWork, String login, int value )
            throws AuctionNotExistsException, WorkNotInAuctionException, UserNotExistsException, BidValueUnderMinValueException {
        User user = users.find( login );
        if( user == null )
            throw new UserNotExistsException();

        AuctionPrivate auction = (AuctionPrivate) auctions.find( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        Work work = works.find( idWork );
        if( work == null )
            throw new WorkNotInAuctionException();

        auction.bid( work, user, value );
    }

    @Override
    public Iterator<WorkInAuction> closeAuction( String idAuction )
            throws AuctionNotExistsException {
        AuctionPrivate auction = (AuctionPrivate) auctions.find( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        return auction.closeAuction( worksSoldOrderedByValue );
    }

    @Override
    public Iterator<WorkInAuction> listAuctionWorks( String idAuction )
            throws AuctionNotExistsException, AuctionEmptyException {
        Auction auction = auctions.find( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        if( auction.emptyAuction() )
            throw new AuctionEmptyException();

        return auction.worksInAuctionIterator();
    }

    @Override
    public Iterator<Entry<String , Work>> listArtistWorks( String login )
            throws UserNotExistsException, ArtistNotExistsException, ArtistWithoutWorksException {
        User userArtist = users.find( login );
        if ( userArtist == null)
            throw new UserNotExistsException();

        if ( !(userArtist instanceof Artist) )
            throw new ArtistNotExistsException();

        Artist artist = (Artist)userArtist;

        Iterator<Entry<String ,Work>> it = artist.worksIterator();
        if ( !it.hasNext() )
            throw new ArtistWithoutWorksException();

        return it;
    }

    @Override
    public Iterator<Bid> listBidsWork( String idAuction, String idWork )
            throws AuctionNotExistsException, WorkNotInAuctionException, WorkWithoutBidsException {
        Auction auction = auctions.find( idAuction );
        if( auction == null )
            throw new AuctionNotExistsException();

        Work work = works.find( idWork );
        if( work == null )
            throw new WorkNotInAuctionException();

        return auction.listBidsWork( work );

    }

    @Override
    public Iterator<Entry<Work,Work>> listWorksByValue()
            throws NoWorkHasBeenActionedException {
        if(worksSoldOrderedByValue.isEmpty())
            throw new NoWorkHasBeenActionedException();

        return worksSoldOrderedByValue.iterator();
    }


    /**
     * Removes all works that were made by a specific user
     *
     * @param creator - Artist object, that must be valid, that may have works made by him
     */
    private void removeAllWorksFromAnArtist( Artist creator ){
        Iterator<Entry<String,Work>> it = creator.worksIterator();
        Work work;
        while(it.hasNext()){
            work = it.next().getValue();
            works.remove(work.getId());
            worksSoldOrderedByValue.remove(work);
        }
    }
}

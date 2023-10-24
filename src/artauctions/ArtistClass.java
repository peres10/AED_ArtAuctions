package artauctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * @author Alexandre Peres 61615
 */
public class ArtistClass extends UserClass implements ArtistPrivate {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * Artist artistic name
     */
    private final String artisticName;

    /**
     * Number of auctioned works a artist has
     */
    private int numberOfAuctionWorks;

    /**
     * List of works made by the artist
     */
    private List<Work> works;

    /**
     * ArtistClass constructor
     *
     * @param login - User's login
     * @param name  - User's name
     * @param age   - User's age
     * @param email - User's email
     */
    protected ArtistClass( String login, String name, int age, String email, String artisticName ) {
        super(login, name, age, email);
        this.artisticName = artisticName;
        this.numberOfAuctionWorks = 0;
        this.works = new DoubleList<>();
    }

    @Override
    public String getArtisticName() {
        return artisticName;
    }

    @Override
    public int getNumberOfAuctionWorks() {
        return numberOfAuctionWorks;
    }

    @Override
    public Iterator<Work> worksIterator() {
        return works.iterator();
    }

    @Override
    public void auctionWork() {
        numberOfAuctionWorks++;
    }

    @Override
    public void closeAuctionWork() {
        numberOfAuctionWorks--;
    }

    @Override
    public void addWork(Work work) {
        works.addLast(work);
    }
}

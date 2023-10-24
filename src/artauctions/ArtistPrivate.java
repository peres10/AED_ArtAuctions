package artauctions;

/**
 * @author Alexandre Peres 61615
 */
public interface ArtistPrivate extends Artist {

    /**
     * Increases the number of works of the artist currently auctioned
     */
    void auctionWork();

    /**
     * Decreases the number of works of the artist currently auctioned
     */
    void closeAuctionWork();

    /**
     * Adds a work to the list of works made by the artist
     *
     * @param work - work that is made by the artist
     */
    void addWork( Work work );
}

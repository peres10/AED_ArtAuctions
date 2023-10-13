package artauctions;

import dataStructures.Iterator;

/**
 * @author Alexandre Peres 61615
 */
public interface Artist extends User {

    /**
     * Returns the Artist's artistic name
     *
     * @return - String with the Artist's artistic name
     */
    String getArtisticName();

    /**
     * Returns the number of works the Artist has auctioned
     *
     * @return - int with the number of works the Artist has auctioend
     */
    int getNumberOfAuctionWorks();

    /**
     * Returns the iterator of works made by the artist
     *
     * @return - Iterator with all the works made by the artist
     */
    Iterator<Work> worksIterator();

}

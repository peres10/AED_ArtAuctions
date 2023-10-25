package artauctions;

import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface AuctionPrivate extends Auction, Serializable {

    /**
     * Adds an artwork to the list of artworks in the auction
     *
     * @param work - Work to be added
     * @param minValue - minimum value for the auction
     */
    void addWorkAuction( WorkPrivate work, int minValue );

    Iterator<WorkInAuction> closeAuction();
}

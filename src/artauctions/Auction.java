package artauctions;

import artauctions.exceptions.WorkNotInAuctionException;
import artauctions.exceptions.WorkWithoutBidsException;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface Auction extends Serializable {

    /**
     * Returns the ID of an auction
     *
     * @return - String with the ID of the auction
     */
    String getId();

    /**
     * Checks if a auction is empty or has any works
     *
     * @return - true if the auction is empty, false if not
     */
    boolean emptyAuction();

    /**
     * Returns an iterator of WorkInAuctoion's
     *
     * @return - iterator of WorkInAuctoion's
     */
    Iterator<WorkInAuction> worksInAuctionIterator();

    /**
     * Closes an auction and returns an iterator of all the works in the auction
     *
     * @return - iterator of all the works in the auction
     */
    Iterator<WorkInAuction> closeAuction();


    Iterator<Bid> listBidsWork( Work work )
            throws WorkNotInAuctionException, WorkWithoutBidsException;

}

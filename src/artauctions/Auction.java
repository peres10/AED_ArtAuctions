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
     * Returns an Iterator of bids on a work
     *
     * @param work - Work in the auction
     * @return - Iterator of bids on a work
     * @throws WorkNotInAuctionException - if Work is not auctioned in this auction
     * @throws WorkWithoutBidsException - if Work in this auction has no bids
     */
    Iterator<Bid> listBidsWork( Work work )
            throws WorkNotInAuctionException, WorkWithoutBidsException;

}

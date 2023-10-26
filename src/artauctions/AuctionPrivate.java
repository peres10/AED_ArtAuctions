package artauctions;

import artauctions.exceptions.BidValueUnderMinValueException;
import artauctions.exceptions.WorkNotInAuctionException;
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

    /**
     * Closes and auction and processes all works in that auction
     * in the end of it
     *
     * @return - Iterator of WorkInAuction with all works in the auction
     */
    Iterator<WorkInAuction> closeAuction();

    /**
     * Bids on a art work in the auction
     *
     * @param idWork - Work's ID
     * @param user - User who is bidding
     * @param value - value of the bid
     * @throws WorkNotInAuctionException - if the idWork is not the ID of a work
     *              in the list of auctioned works
     * @throws BidValueUnderMinValueException - if the value of the bid is smaller
     *              than the minimum value to bid in the auction
     */
    void bid( Work work, User user, int value)
            throws WorkNotInAuctionException, BidValueUnderMinValueException;
}

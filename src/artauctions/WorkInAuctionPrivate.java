package artauctions;

import artauctions.exceptions.BidValueUnderMinValueException;
import dataStructures.OrderedDictionary;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface WorkInAuctionPrivate extends WorkInAuction, Serializable {

    /**
     * Ends the auction on this specific artwork and does all the processes
     * to determine the sale value or if it was not sold
     *
     * @param worksSoldOrderedByValue - the ordered dicitonary of the works ordered by value
     */
    void endAuction( OrderedDictionary<Work,Work> worksSoldOrderedByValue );


    /**
     * Bids in the work of art
     *
     * @param user - user who is bidding
     * @param value - value the user will bid
     * @throws BidValueUnderMinValueException - if value < minValue
     */
    void bid( User user, int value ) throws BidValueUnderMinValueException;

}

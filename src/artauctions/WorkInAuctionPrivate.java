package artauctions;

import artauctions.exceptions.BidValueUnderMinValueException;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface WorkInAuctionPrivate extends WorkInAuction, Serializable {

    /**
     * Ends the auction on this specific artwork and does all the processes
     * to determine the sale value or if it was not sold
     */
    void endAuction();


    /**
     * Bids in the work of art
     *
     * @param user - user who is bidding
     * @param value - value the user will bid
     * @throws BidValueUnderMinValueException - if value < minValue
     */
    void bid( User user, int value ) throws BidValueUnderMinValueException;

}

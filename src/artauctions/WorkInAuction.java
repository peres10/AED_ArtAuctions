package artauctions;

import artauctions.exceptions.WorkWithoutBidsException;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface WorkInAuction extends Serializable {

    /**
     * Gets the object of the art work
     *
     * @return - Work object of an  art work
     */
    Work getWork();

    /**
     * Returns a boolean telling if a work was sold or not
     *
     * @return - true if it was, false if not
     */
    boolean getIfWasSold();

    /**
     * Returns the minimum value of a bid for a art work
     * in this auction
     *
     * @return - int with the minimum value
     */
    int getMinValue();

    /**
     * Returns an iterator of all Bid's in this auction
     *
     * @return - Iterator of all Bid's in this auction
     * @throws WorkWithoutBidsException - if there is no bids yet
     */
    Iterator<Bid> getBids() throws WorkWithoutBidsException;

    /**
     * Returns the login of the winner of the auction
     *
     * @return - String with the login
     */
    String getLoginBuyer();

    /**
     * Returns the name of the winner of the auction
     *
     * @return - String with the name
     */
    String getNameBuyer();

    /**
     * Returns the value of the winning bid
     *
     * @return - int with the value
     */
    int getSaleValue();
}

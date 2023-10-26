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


    Iterator<Bid> getBids() throws WorkWithoutBidsException;

    String getLoginBuyer();

    String getNameBuyer();

    int getSaleValue();
}

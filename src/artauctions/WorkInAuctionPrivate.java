package artauctions;

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
}

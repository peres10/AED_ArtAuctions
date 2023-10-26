package artauctions;

import java.io.Serializable;

public interface BidPrivate extends Bid, Serializable {

    /**
     * Removes a bid from a user (only used when a auction is closed)
     */
    void removeBidFromUser();
}

package artauctions;

import dataStructures.DoubleList;
import dataStructures.List;

/**
 * @author Alexandre Peres 61615
 */
public class WorkInAuctionClass implements WorkInAuctionPrivate {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * Work object
     */
    private final WorkPrivate work;
    /**
     * Minimum value of the auction of this work
     */
    private final int minValue;
    /**
     * List of all bids made for this work in this auction
     */
    private List<Bid> bids;
    /**
     * Highest bid made for this work in this auction
     */
    private Bid highestBid;
    /**
     * It's changed to true if after a auction ends the work was sold to someone
     */
    private boolean wasSold;
    /**
     * WorkInAuctionClass
     *
     * @param work - Work's object
     * @param minValue - int with minimum value of the auction of this work
     */
    public WorkInAuctionClass(WorkPrivate work, int minValue) {
        this.work = work;
        this.minValue = minValue;
        bids = new DoubleList<>();
        highestBid = null;
        wasSold = false;
        work.addToAnAuction();
    }

    @Override
    public Work getWork() {
        return work;
    }

    @Override
    public boolean getIfWasSold() {
        return wasSold;
    }

    @Override
    public void endAuction() {
        if(highestBid != null) {
            work.sellArtWork(0); //work.sellArtWork(highestBid.getValue())
            wasSold = true;
        }
        work.removeFromAnAuction();
    }
}

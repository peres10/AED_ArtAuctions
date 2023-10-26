package artauctions;

import artauctions.exceptions.BidValueUnderMinValueException;
import artauctions.exceptions.WorkWithoutBidsException;
import dataStructures.DoubleList;
import dataStructures.Iterator;
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
    public int getMinValue() {
        return minValue;
    }

    @Override
    public Iterator<Bid> getBids() throws WorkWithoutBidsException {
        if( bids.isEmpty() )
            throw new WorkWithoutBidsException();

        return bids.iterator();
    }

    @Override
    public String getLoginBuyer() {
        return highestBid.userLogin();
    }

    @Override
    public String getNameBuyer() {
        return highestBid.userName();
    }

    @Override
    public int getSaleValue() {
        return highestBid.getValue();
    }

    @Override
    public void endAuction() {
        if(highestBid != null) {
            work.sellArtWork(highestBid.getValue()); //work.sellArtWork(highestBid.getValue())
            wasSold = true;
        }
        work.removeFromAnAuction();
        removeBids();
    }

    @Override
    public void bid(User user, int value) throws BidValueUnderMinValueException {
        if(value < minValue){
            throw new BidValueUnderMinValueException();
        }

        Bid bid = new BidClass( user, value );

        if (highestBid == null || value > highestBid.getValue())
            highestBid = bid;

        bids.addLast(bid);
    }

    /**
     * Removes all bids from the owner of the bid, by calling a function that
     * decreases that number of active bids the user has
     */
    private void removeBids(){
        Iterator<Bid> it = bids.iterator();
        BidPrivate bid;
        while(it.hasNext()){
            bid = (BidPrivate) it.next();
            bid.removeBidFromUser();
        }
    }



}

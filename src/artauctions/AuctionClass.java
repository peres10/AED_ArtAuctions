package artauctions;

import artauctions.exceptions.BidValueUnderMinValueException;
import artauctions.exceptions.WorkNotInAuctionException;
import artauctions.exceptions.WorkWithoutBidsException;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.OrderedDictionary;

/**
 * @author Alexandre Peres 61615
 */
public class AuctionClass implements AuctionPrivate{

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 0L;
    /**
     * Auction ID
     */
    private final String id;
    /**
     * List of works in the auction
     */
    private final List<WorkInAuction> worksInAuction;

    /**
     * AuctionClass constructor
     *
     * @param id - Auction's ID
     */
    public AuctionClass( String id ){
        this.id = id;
        worksInAuction = new DoubleList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean emptyAuction() {
        return worksInAuction.isEmpty();
    }

    @Override
    public Iterator<WorkInAuction> worksInAuctionIterator() {
        return worksInAuction.iterator();
    }

    @Override
    public void addWorkAuction( WorkPrivate work, int minValue ) {
        if(findWork(work) == null) {
            worksInAuction.addLast(new WorkInAuctionClass(work, minValue));
        }
    }

    @Override
    public Iterator<WorkInAuction> closeAuction( OrderedDictionary<Work,Work> worksSoldOrderedByValue ) {
        endEachWorkInAuction( worksSoldOrderedByValue );
        return worksInAuction.iterator();
    }

    @Override
    public Iterator<Bid> listBidsWork(Work work) throws WorkNotInAuctionException, WorkWithoutBidsException {
        WorkInAuction workInAuction = findWork( work );
        if( workInAuction == null)
            throw new WorkNotInAuctionException();

        return workInAuction.getBids();
    }

    @Override
    public void bid(Work work, User user, int value) throws WorkNotInAuctionException, BidValueUnderMinValueException {
        WorkInAuctionPrivate workInAuction = (WorkInAuctionPrivate) findWork( work );
        if( workInAuction == null)
            throw new WorkNotInAuctionException();

        workInAuction.bid( user, value );
    }

    /**
     * Find if a work is already auctioned
     *
     * @param work - the work to be searched
     * @return - if the work is in the auction returns the WorkInAuction object, if not returns null
     */
    private WorkInAuction findWork( Work work ){
        Iterator<WorkInAuction> it = worksInAuction.iterator();
        WorkInAuction workInAuctionObj;
        while(it.hasNext()){
            workInAuctionObj = it.next();
            if(workInAuctionObj.getWork().getId().equalsIgnoreCase(work.getId()))
                return workInAuctionObj;
        }
        return null;
    }


    /**
     * Process all works in the end of an auction
     *
     * @param worksSoldOrderedByValue - the ordered dicitonary of the works ordered by value
     */
    private void endEachWorkInAuction( OrderedDictionary<Work,Work> worksSoldOrderedByValue ){
        Iterator<WorkInAuction> it = worksInAuction.iterator();
        WorkInAuctionPrivate workInAuctionObj;
        while(it.hasNext()){
            workInAuctionObj = (WorkInAuctionPrivate) it.next();
            workInAuctionObj.endAuction( worksSoldOrderedByValue );
        }
    }
}

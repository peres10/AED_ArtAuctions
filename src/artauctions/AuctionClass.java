package artauctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * @author Alexandre Peres 61615
 */
public class AuctionClass implements AuctionPrivate{

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * Auction ID
     */
    private final String id;
    /**
     * List of works in the auction
     */
    private List<WorkInAuction> worksInAuction;

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
        if(!findIfWorkAlreadyInAuction(work)) {
            worksInAuction.addLast(new WorkInAuctionClass(work, minValue));
        }
    }


    /**
     * Find if a work is already auctioned
     *
     * @param work - the work to be searched
     * @return - true if it is, false if not
     */
    private boolean findIfWorkAlreadyInAuction( Work work ){
        Iterator<WorkInAuction> it = worksInAuction.iterator();
        WorkInAuction worksInAuctionIt;
        while(it.hasNext()){
            worksInAuctionIt = it.next();
            if(worksInAuctionIt.getWork().getId().equalsIgnoreCase(work.getId()))
                return true;
        }
        return false;
    }

}

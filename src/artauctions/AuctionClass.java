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
    private List<Work> worksInAuction;

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
    public void addWorkAuction( WorkPrivate work ) {
        if(!findIfWorkAlreadyInAuction(work)) {
            worksInAuction.addLast(work);
            work.addToAnAuction();
        }
    }

    private boolean findIfWorkAlreadyInAuction( Work work ) {
        Iterator<Work> it = worksInAuction.iterator();
        Work workIt;
        while (it.hasNext()){
            workIt = it.next();
            if(workIt.getId().equalsIgnoreCase(work.getId()))
                return true;
        }
        return false;
    }

}

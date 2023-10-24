package artauctions;

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
     * WorkInAuctionClass
     *
     * @param work - Work's object
     * @param minValue - int with minimum value of the auction of this work
     */
    public WorkInAuctionClass(WorkPrivate work, int minValue) {
        this.work = work;
        this.minValue = minValue;
        work.addToAnAuction();
    }

    @Override
    public Work getWork() {
        return work;
    }
}

package artauctions;

/**
 * @author Alexandre Peres 61615
 */
public class BidClass implements BidPrivate{

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 0L;
    /**
     * User who made the bid
     */
    private final User user;
    /**
     * Value of the bid
     */
    private final int value;

    /**
     * BidClass constructor
     *
     * @param user - User who made the mid
     * @param value - int with the value of the bid
     */
    public BidClass( User user, int value ){
        this.user = user;
        this.value = value;
        addBidToUser();
    }

    @Override
    public void removeBidFromUser() {
        UserPrivate userSetter = (UserPrivate)user;
        userSetter.removeBid();
    }

    /**
     * Makes changes in the user so it know it bidded on an auction
     */
    private void addBidToUser(){
        UserPrivate userSetter = (UserPrivate)user;
        userSetter.bidOnWorkOfArt();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String userLogin() {
        return user.getLogin();
    }

    @Override
    public String userName() {
        return user.getName();
    }
}

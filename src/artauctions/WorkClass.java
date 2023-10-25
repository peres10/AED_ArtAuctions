package artauctions;

/**
 * @author Alexandre Peres 61615
 */
public class WorkClass implements WorkPrivate{

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * Work's id
     */
    private final String id;
    /**
     * Work's creator
     */
    private final Artist creator;
    /**
     * Work's year
     */
    private final int year;
    /**
     * Work's name
     */
    private final String name;
    //private boolean hasBeenSoldOnce;
    /**
     * Work's last sale value
     */
    private int lastSaleValue;
    /**
     * Work's highest sale value
     */
    private int highestSaleValue;


    /**
     * WorkClass constructor
     *
     * @param idWork - Work's id
     * @param creator - Work's creator
     * @param year - Work's year
     * @param name - Work's name
     */
    public WorkClass( String idWork, Artist creator, int year, String name){
        this.id = idWork;
        this.creator = creator;
        this.year = year;
        this.name = name;
        this.lastSaleValue = 0;
        this.highestSaleValue = 0;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public Artist getCreator() {
        return creator;
    }

    @Override
    public String getCreatorName() {
        return creator.getName();
    }

    @Override
    public String getCreatorLogin() {
        return creator.getLogin();
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLastSaleValue() {
        return lastSaleValue;
    }

    @Override
    public int getHighestSaleValue() {
        return highestSaleValue;
    }

    @Override
    public void sellArtWork( int value ) {
        if( value > highestSaleValue)
            highestSaleValue = value;
        lastSaleValue = value;
    }

    @Override
    public void addToAnAuction() {
        ((ArtistPrivate)creator).auctionWork();
    }

    @Override
    public void removeFromAnAuction() {
        ((ArtistPrivate)creator).closeAuctionWork();
    }

}

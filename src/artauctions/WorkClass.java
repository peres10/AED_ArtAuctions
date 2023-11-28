package artauctions;

import dataStructures.OrderedDictionary;

/**
 * @author Alexandre Peres 61615
 */
public class WorkClass implements WorkPrivate{

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 0L;

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
    /**
     * Work's last sale value
     */
    private int lastSaleValue;
    /**
     * Work's highest sale value
     */
    private int highestSaleValue;
    /**
     * If the work of art has ever been sold
     */
    private boolean hasBeenSold;

    /**
     * WorkClass constructor
     *
     * @param idWork - Work's id
     * @param creator - Work's creator
     * @param year - Work's year
     * @param name - Work's name
     */
    public WorkClass( String idWork, Artist creator, int year, String name ){
        this.id = idWork;
        this.creator = creator;
        this.year = year;
        this.name = name;
        this.lastSaleValue = 0;
        this.highestSaleValue = 0;
        this.hasBeenSold = false;
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
    public boolean haveHasEverBeenSold() {
        return hasBeenSold;
    }

    @Override
    public void sellArtWork( int value, OrderedDictionary<Work,Work> worksSoldOrderedByValue ) {
        if(hasBeenSold && value < highestSaleValue){
            lastSaleValue = value;
        } else {
            if(hasBeenSold) {
                worksSoldOrderedByValue.remove(this);
            } else {
                hasBeenSold = true;
            }
            highestSaleValue = value;
            lastSaleValue = value;
            worksSoldOrderedByValue.insert(this,this);
        }
    }

    @Override
    public void addToAnAuction() {
        ArtistPrivate artistP = (ArtistPrivate) creator;
        artistP.auctionWork();
    }

    @Override
    public void removeFromAnAuction() {
        ArtistPrivate artistP = (ArtistPrivate) creator;
        artistP.closeAuctionWork();
    }

    //orders from highest sale value to lowest sale value, if it draws then it's order by name
    @Override
    public int compareTo( Work o ) {
        if(highestSaleValue > o.getHighestSaleValue())
            return -1;
        else if(highestSaleValue < o.getHighestSaleValue())
            return 1;
        else
            return name.compareTo(o.getName());
    }
}

package artauctions;

import dataStructures.OrderedDictionary;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface WorkPrivate extends Work, Serializable {

    /**
     * Marks if a art work has been sold for the first time (if needed)
     * sets the last sale value, and if needed sets the new highest sale value
     *
     * @param value - int with the value the art work was sold
     */
    void sellArtWork( int value, OrderedDictionary<Work,Work> worksSoldOrderedByValue );

    /**
     * Increases the creator's of this art work number of works in auction
     */
    void addToAnAuction();

    /**
     * Decreases the creator's of this art work number of works in auction
     */
    void removeFromAnAuction();
}

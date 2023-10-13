package artauctions;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface UserPrivate extends User,Serializable {

    /**
     * Increases the number of active bids a User has
     */
    void bidOnWorkOfArt();

    /**
     * Decreases the number of active bids a User has
     */
    void removeBid();

}

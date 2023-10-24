package artauctions;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface Auction extends Serializable {

    /**
     * Returns the ID of an auction
     *
     * @return
     */
    String getId();
}

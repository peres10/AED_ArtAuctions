package artauctions;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface WorkPrivate extends Work, Serializable {

    /**
     * Marks if a art work has been sold for the first time (if needed)
     * sets the last sale value, and if needed sets the new highest sale value
     */
    void sellArtWork( int value );

}

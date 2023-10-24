package artauctions;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface WorkInAuction extends Serializable {

    /**
     * Gets the object of the art work
     *
     * @return - Work object of an  art work
     */
    Work getWork();
}

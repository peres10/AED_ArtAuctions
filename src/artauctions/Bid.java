package artauctions;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface Bid extends Serializable {

    /**
     * Returns the value of the bid
     *
     * @return - int with the value of the bid
     */
    int getValue();

    /**
     * Returns the login of the user who made the bid
     *
     * @return - String with the login of the user
     */
    String userLogin();

    /**
     * Returns the name of the user who made the bid
     *
     * @return - String with the name of the user
     */
    String userName();
}

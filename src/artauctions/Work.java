package artauctions;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface Work extends Serializable {

    /**
     * Returns the id of an art Work
     *
     * @return - String with the id
     */
    String getId();

    /**
     * Returns the creator of the art Work
     *
     * @return - an Artist, creator of the art work
     */
    Artist getCreator();

    /**
     * Returns the name of the creator of the art work
     *
     * @return - String with the name of the art work creator
     */
    String getCreatorName();

    /**
     * Returns the login of the creator of the art work
     *
     * @return - String with the login of the art work creator
     */
    String getCreatorLogin();

    /**
     * Returns the year of the art work
     *
     * @return - int with the year of the art work
     */
    int getYear();

    /**
     * Returns the name of the art work
     *
     * @return - String with the name of the art work
     */
    String getName();

    /**
     * Gets if a the art work has ever been sold
     *
     * @return - boolean, true if has ever been sold, false if not
     */
    //boolean getIfHasBeenSoldOnce();

    /**
     * Returns the value of the last sale of the art work
     *
     * @return - int with the value of the last sale of the art work
     */
    int getLastSaleValue();

    /**
     * Returns the value of the highest sale of the art work
     *
     * @return - int with the value of the highest sale of the art work
     */
    int getHighestSaleValue();


}

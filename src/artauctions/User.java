package artauctions;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 */
public interface User extends Serializable {

    /**
     * Returns the User's login
     *
     * @return - String with the User's login
     */
    String getLogin();

    /**
     * Returns the User's name
     *
     * @return - String with the User's name
     */
    String getName();

    /**
     * Returns the User's age
     *
     * @return - int with the User's int
     */
    int getAge();

    /**
     * Returns the User's email
     *
     * @return - String with the User's email
     */
    String getEmail();
}

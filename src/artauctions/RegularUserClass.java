package artauctions;

/**
 * @author Alexandre Peres 61615
 */
public class RegularUserClass extends AbstractUser implements RegularUser {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;


    /**
     * RegularUserClass constructor
     *
     * @param login - User's login
     * @param name  - User's name
     * @param age   - User's age
     * @param email - User's email
     */
    protected RegularUserClass(String login, String name, int age, String email) {
        super(login, name, age, email);
    }
}

package artauctions;

/**
 * AbstractUser class contaning every information that any type of user may have
 *
 * @author Alexandre Peres 61615
 */
public abstract class AbstractUser implements User {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * User's login
     */
    private final String login;
    /**
     * User's name
     */
    private final String name;
    /**
     * User's age
     */
    private final int age;
    /**
     * User's email
     */
    private final String email;

    /**
     * AbstractUser constructor
     *
     * @param login - User's login
     * @param name - User's name
     * @param age - User's age
     * @param email - User's email
     */
    protected AbstractUser( String login, String name, int age, String email){
        this.login = login;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}

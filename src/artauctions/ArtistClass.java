package artauctions;

public class ArtistClass extends AbstractUser implements Artist {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;


    /**
     * ArtistClass constructor
     *
     * @param login - User's login
     * @param name  - User's name
     * @param age   - User's age
     * @param email - User's email
     */
    protected ArtistClass(String login, String name, int age, String email) {
        super(login, name, age, email);
    }
}

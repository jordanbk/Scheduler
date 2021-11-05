package C195.Model;

/** * The Contact class * @author Jordan Burke */
public class Contact {
    private int id;
    private String name;
    private String email;

    /** Contact Constructor
     *
     * @param id Contact ID
     * @param name Contact Name
     * @param email Contact Email
     */
    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /** Getter for Contact ID
     *
     * @return Contact ID
     */
    public int getId() {
        return id;
    }

    /** Setter for Contact ID
     *
     * @param id Contact ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Getter for Contact Name
     *
     * @return Contact Name
     */
    public String getName() {
        return name;
    }

    /** Setter for Contact Name
     *
     * @param name Contact Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Getter for Contact Email
     *
     * @return Contact Email
     */
    public String getEmail() {
        return email;
    }

    /** Setter for Contact Email
     *
     * @param email Contact Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }

}

package C195.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/** * The Appointment class * @author Jordan Burke */
public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;
    private int contactId;

    //loads types in observable list
    public static ObservableList<String> types = FXCollections.observableArrayList("Stand Up", "All Hands", "Demos",
            "Brain Storm");

    /**
     * Appointment constructor
     * @param id appointment id
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param start appointment start
     * @param end  appointment end
     * @param customerId customer ID
     * @param userId user ID
     * @param contactId contact ID
     */
    public Appointment(int id, String title, String description, String location, String type, LocalDateTime start,
                       LocalDateTime end, int customerId, int userId, int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    public Appointment() {

    }

    /** Getter for appointment ID
     *
     * @return appointment ID
     */
    public int getId() {
        return id;
    }

    /** Setter for appointment ID
     *
     * @param id appointment ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Getter for appointment title
     *
     * @return appointment title
     */
    public String getTitle() {
        return title;
    }

    /** Setter for appointment title
     *
     * @param title appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Getter for appointment description
     *
     * @return appointment description
     */
    public String getDescription() {
        return description;
    }

    /** Setter for appointment description
     *
     * @param description appointment description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Getter for appointment location
     *
     * @return appointment location
     */
    public String getLocation() {
        return location;
    }

    /** Setter for appointment location
     *
     * @param location appointment location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /** Getter for appointment type
     *
     * @return appointment type
     */
    public String getType() {
        return type;
    }

    /** Setter for appointment type
     *
     * @param type appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Getter for appointment start
     *
     * @return appointment start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /** Setter for appointment start
     *
     * @param start appointment start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /** Getter for appointment end
     *
     * @return appointment end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /** Setter for appointment end
     *
     * @param end appointment end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /** Getter for Customer ID
     *
     * @return Customer ID affiliated with appointment
     */
    public int getCustomerId() {
        return customerId;
    }

    /** Setter for Customer ID
     *
     * @param customerId Customer ID affiliated with appointment
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Getter for User ID
     *
     * @return user ID affiliated with appointment
     */
    public int getUserId() {
        return userId;
    }

    /** Setter for User ID
     *
     * @param userId User ID affiliated with appointment
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Getter for Contact ID
     *
     * @return Contact ID affiliated with appointment
     */
    public int getContactId() {
        return contactId;
    }

    /** Setter for Contact ID
     *
     * @param contactId Contact ID affiliated with appointment
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** Getter for appointment start time
     *
     * @return start time
     */
    public LocalDateTime getStartTime() {
        return start;
    }

    /** Setter for appointment start time
     *
     * @param start appointment start time
     */
    public void setStartTime(LocalDateTime start) {
        this.start = start;
    }


}

package C195.Model;

import java.security.Timestamp;
import java.util.TimeZone;

/** * The User class * @author Jordan Burke */

public class User {
    public int userId;
    public String userName;
    public String password;
    public Timestamp createDate;
    public String createdBy;
    public Timestamp lastUpdate;
    public String lastUpdateBy;
    public TimeZone timeZone;

    /** User Constructor
     *
     * @param userId the User ID
     * @param userName the User Name
     * @param password the Password
     * @param createDate the Create Date
     * @param createdBy the Created By
     * @param lastUpdate the Last Update
     * @param lastUpdateBy the Last Updated By
     */
    public User(int userId, String userName, String password, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public User() {

    }

    /** Getter for the User ID
     *
     * @return User ID
     */
    public int getUserId() {
        return userId;
    }

    /** Setter for the User ID
     *
     * @param userId User ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Getter for User Name
     *
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }

    /** Setter for the User Name
     *
     * @param userName User Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Getter for the Password
     *
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /** Setter for the Password
     *
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Getter for Create Date
     *
     * @return Create Date
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /** Setter for Create Date
     *
     * @param createDate Create Date
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /** Getter for Created By
     *
     * @return Created By
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** Setter for Created By
     *
     * @param createdBy Created By
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** Getter for Last Update
     *
     * @return Last Update
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /** Setter for Last Update
     *
     * @param lastUpdate Last Update
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** Getter for Last Updated By
     *
     * @return
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /** Setter for Last Updated By
     *
     * @param lastUpdateBy last updated by
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /** Getter for Time Zone
     *
     * @return time zone
     */
    public TimeZone getTimezone() {
        return timeZone;
    }

    /** Setter for Time Zone
     *
     * @param t time zone
     */
    public void setTimezone(TimeZone t) {
        timeZone = t;
    }

    /** User Name and ID to String
     *
     * @return user name and ID to String
     */
    @Override
    public String toString(){
        return (getUserName() + " [" + getUserId() + "]");
    }
}


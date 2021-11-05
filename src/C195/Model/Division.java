package C195.Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** * The Division class * @author Jordan Burke */

public class Division {

    int id;
    String name;
    LocalDateTime createDate;
    String createdBy;
    LocalDateTime lastUpdate;
    String lastUpdatedBy;
    int countryID;

    /** Division Constructor
     *
     * @param divisionID the Division ID
     * @param divisionName the Division Name
     * @param createDate the Division Create Date
     * @param createdBy the Division Created By
     * @param lastUpdate the Division Last Update
     * @param lastUpdatedBy the Division Last Updated By
     * @param countryID the Division Country ID
     */
    public Division(int divisionID, String divisionName, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryID) {
        this.id = divisionID;
        this.name = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /** Getter for Division ID
     *
     * @return Division ID
     */
    public int getDivisionID() {
        return id;
    }

    /** Setter for Division ID
     *
     * @param divisionID Division ID
     */
    public void setDivisionID(int divisionID) {
        this.id = divisionID;
    }

    /** Getter for Division Name
     *
     * @return Division Name
     */
    public String getDivisionName() {
        return name;
    }

    /** Setter for Division Name
     *
     * @param divisionName Division Name
     */
    public void setDivisionName(String divisionName) {
        this.name = divisionName;
    }

    /** Getter for Create Date
     *
     * @return Create Date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /** Setter for Create Date
     *
     * @param createDate Create Date
     */
    public void setCreateDate(LocalDateTime createDate) {
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
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /** Setter for Last Update
     *
     * @param lastUpdate Last Update
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** Getter for Last Updated By
     *
     * @return Last Updated By
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** Setter for Last Updated By
     *
     * @param lastUpdatedBy Last Updated By
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** Getter for Division's Country ID
     *
     * @return Country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /** Setter for Division's Country ID
     *
     * @param countryID Country ID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /** Division Name and ID to String
     *
     * @return Division Name and ID
     */
    @Override
    public String toString(){
        return (getDivisionName() + " [" + getDivisionID() + "]");
    }
}

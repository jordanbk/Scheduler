package C195.Model;

import java.time.LocalDateTime;

/** * The Country class * @author Jordan Burke */
public class Country {
    int id;
    String name;
    LocalDateTime createDate;
    String createdBy;
    LocalDateTime lastUpdate;
    String lastUpdatedBy;

    /** Country Constructor
     *
     * @param countryID the Country ID
     * @param countryName the Country Name
     * @param createDate the Create Date
     * @param createdBy Created By
     * @param lastUpdate Last Update
     * @param lastUpdatedBy Last Updated By
     */
    public Country(int countryID, String countryName, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.id = countryID;
        this.name = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** Getter for Country ID
     *
     * @return Country ID
     */
    public int getCountryID() {
        return id;
    }

    /*** Country Name to String
     *
     * @return Country Name to String
     */
    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }

    /** Setter for Country ID
     *
     * @param countryID Country ID
     */
    public void setCountryID(int countryID) {
        this.id = countryID;
    }

    /** Getter for Country Name
     *
     * @return Country Name
     */
    public String getCountryName() {
        return name;
    }

    /** Setter for Country Name
     *
     * @param countryName Country Name
     */
    public void setCountryName(String countryName) {
        this.name = countryName;
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
}

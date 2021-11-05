package C195.Model;

/** * The Report class * @author Jordan Burke */

public class ReportObject {
    int count;
    String month;
    String type;

    /** Report Constructor
     *
     * @param count the Appointment Count
     * @param month the Appointment Month
     * @param type the Appointment Type
     */
    public ReportObject(int count, String month, String type) {
        this.count = count;
        this.month = month;
        this.type = type;
    }
    public ReportObject() {
    }

    /** Getter for the Appointment Type
     *
     * @return Type
     */
    public String getType() {
        return type;
    }

    /** Setter for the Appointment Type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Getter for the Appointment Month
     *
     * @return Appointment Month
     */
    public String getMonth() {
        return month;
    }

    /** Setter for the Appointment Month
     *
     * @param month Appointment Month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /** Getter for the Appointment Count
     *
     * @return Appointment Count
     */
    public int getCount() {
        return count;
    }

    /** Setter for the Appointment Count
     *
     * @param count Appointment Count
     */
    public void setCount(int count) {
        this.count = count;
    }
}

package C195.Model;

import C195.Database.AppointmentDAO;
import C195.Database.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/** * The Customer class * @author Jordan Burke */
public class Customer {
    private static ObservableList<CustomerDAO> customers = FXCollections.observableArrayList();
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;

    /** Customer Constructor
     *
     * @param id Customer ID
     * @param name Customer Name
     * @param address Customer Address
     * @param postalCode Customer Postal Code
     * @param phone Customer Phone Number
     * @param divisionID Customer Division ID
     */
    public Customer(int id, String name, String address, String postalCode, String phone, int divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
    }
    //Empty Constructor
    public Customer() { }
    //Observable List of Customers
    public static ObservableList<CustomerDAO> getCustomers() {
        return customers;
    }

    /** Observable list of Customers by Appointment ID
     *
     * @param id Appointment ID
     * @return All Appointments
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppts(int id) throws Exception {
        ObservableList<Appointment> allCustomerApts = FXCollections.observableArrayList();
        AppointmentDAO appointmentDao = new AppointmentDAO();

        for (Appointment a : AppointmentDAO.getAllAppointments()){
            if (a.getCustomerId() == id){
                allCustomerApts.add(a);
            }
        }
        return allCustomerApts;
    }

    /** Getter for Customer ID
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /** Setter for Customer Name
     *
     * @return Customer Name
     */
    public String getName() {
        return name;
    }

    /** Getter for Customer Address
     *
     * @return Customer Address
     */
    public String getAddress() {
        return address;
    }

    /** Getter for Customer's Postal Code
     *
     * @return Customer's Postal Code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /** Getter for Customer's Phone Number
     *
     * @return Customer's Phone Number
     */
    public String getPhone() {
        return phone;
    }

    /** Getter for Customer's Division ID
     *
     * @return Customer's Division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /** Setter for Observable List of Customers
     *
     * @param customers
     */
    public static void setCustomers(ObservableList<CustomerDAO> customers) {
        Customer.customers = customers;
    }

    /** Setter for Customer ID
     *
     * @param id Customer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Setter for Customer Name
     *
     * @param name Customer Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Setter for Customer's Address
     *
     * @param address Customer's Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Setter for Customer's Postal Code
     *
     * @param postalCode Customer's Postal Code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** Setter for Customer's Phone Number
     *
     * @param phone Customer's Phone Number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Setter for Customer's Division ID
     *
     * @param divisionID Customer's Division ID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /** Customer Name and ID to String
     *
     * @return Customer Name and ID
     */
    @Override
    public String toString(){
        return (getName() + " [" + getId() + "]");
    }
}

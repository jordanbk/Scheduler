package C195.Model;

import C195.Database.AppointmentDAO;
import C195.Database.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Customer {
    private static ObservableList<CustomerDAO> customers = FXCollections.observableArrayList();
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;


    public Customer(int id, String name, String address, String postalCode, String phone, int divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
    }

    public Customer() {

    }

    public static ObservableList<CustomerDAO> getCustomers() {
        return customers;
    }

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public int getDivisionID() {
        return divisionID;
    }

    //setters

    public static void setCustomers(ObservableList<CustomerDAO> customers) {
        Customer.customers = customers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    @Override
    public String toString(){
        return (getName() + " [" + getId() + "]");
    }
}

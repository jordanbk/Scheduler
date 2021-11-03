package C195.Database;

import C195.Model.Appointment;
import C195.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** * This class controls the Customer data * @author Jordan Burke */
public class CustomerDAO implements Initializable  {

    /**
     *  Update customer based on updated customer attributes
     *  Using sql UPDATE statement with question marks
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerZip
     * @param customerPhone
     * @param divisionID
     * @throws SQLException
     */
    public void updateCustomer(int customerID, String customerName, String customerAddress, String customerZip, String customerPhone, int divisionID) throws SQLException {
        String updateCustomer = ("UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?");
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(updateCustomer);

        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerZip);
        ps.setString(4, customerPhone);
        ps.setInt(5, divisionID);
        ps.setInt(6, customerID);

        ps.execute();
    }

    /**
     *  Retrieve all customer details from the database by connecting to database
     *   and inserting values retrieved from app fields
     * @return all customers
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException{
        ObservableList <Customer> allCustomers = FXCollections.observableArrayList();

        DatabaseConnection.openConnection();
        String sqlStatement = "select * from customers";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sqlStatement);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");

            Customer customerResult = new Customer(id,name, address, postalCode, phone, divisionID);
            allCustomers.add(customerResult);
        }
        return allCustomers;
    }

    /**
     *  Inserting new customer with customer attributes by connecting to database
     *  and inserting values retrieved from app fields with sql statement
     * @param customerName customer name
     * @param customerAddress customer address
     * @param customerZip customer zip code
     * @param customerPhone customer phone number
     * @param divisionID division ID affiliated with customer
     * @throws SQLException
     */
    public void addCustomer(String customerName, String customerAddress, String customerZip, String customerPhone, int divisionID) throws SQLException {

        String addCustomer = ("INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Created_By, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement preparedStmt = DatabaseConnection.getConnection().prepareStatement(addCustomer);

        preparedStmt.setString(1, customerName);
        preparedStmt.setString(2, customerAddress);
        preparedStmt.setString(3, customerZip);
        preparedStmt.setString(4, customerPhone);
        preparedStmt.setString(5, "admin");
        preparedStmt.setString(6, "admin");
        preparedStmt.setInt(7, divisionID);

        preparedStmt.execute();
    }

    /**
     *  Removing customer by connecting to the database and using the Delete statement in sql
     * @param customerID customer ID
     * @throws SQLException
     */
    public static void deleteCustomer(int customerID) throws SQLException {
        String deleteCustomerPs = "DELETE FROM customers WHERE Customer_ID = ?";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(deleteCustomerPs);
        ps.setInt(1, customerID);
        ps.execute();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

package C195.Controller;

import C195.Database.AppointmentDAO;
import C195.Database.ContactDAO;
import C195.Database.CustomerDAO;
import C195.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class ReportsController implements Initializable {
    Stage stage;
    Parent root;
    ReportObject report = new ReportObject();
    @FXML private Tab tabCountApptTypeMonth;
    @FXML private TableView<ReportObject> tab1Table;
    @FXML private TableView<Appointment> tab2Table;
    @FXML private TableView<Appointment> tab3Table;
    @FXML private Button tab2ReportGen;
    @FXML private Button tab1MainMenu;
    @FXML private TableColumn<ReportObject, Integer> countCol;
    @FXML private TableColumn<ReportObject, String> MonthCol;
    @FXML private TableColumn<ReportObject, String> tab1TypeCol;
    @FXML private ComboBox<String> tab1Type;
    @FXML private ComboBox<String> tab1Month;
    @FXML private Tab tabApptByCustomer;
    @FXML private ComboBox<Integer> tab2Customer;
    @FXML private TableColumn<Appointment, Integer> apptIdCol;
    @FXML private TableColumn<Appointment, String> titleCol;
    @FXML private TableColumn<Appointment, String> typeCol;
    @FXML private TableColumn<Appointment, String> descCol;
    @FXML private TableColumn<Appointment, String> startCol;
    @FXML private TableColumn<Appointment, String> endCol;
    @FXML private TableColumn<Appointment, Integer> contactIdCol;
    @FXML private Button tab2MainMenu;
    @FXML private Tab tabApptByContact;
    @FXML private TableColumn<Appointment, Integer> apptIdCol1;
    @FXML private TableColumn<Appointment, String> titleCol1;
    @FXML private TableColumn<Appointment, String> typeCol1;
    @FXML private TableColumn<Appointment, String> descCol1;
    @FXML private TableColumn<Appointment, String> startCol1;
    @FXML private TableColumn<Appointment, String> endCol1;
    @FXML private TableColumn<Appointment, Integer> custIdCol1;
    @FXML private ComboBox<Integer> tab3Contact;
    @FXML private Button tab3MainMenu;
    public void tab2Customer() throws SQLException { }
    public void customerComboBox(javafx.event.ActionEvent actionEvent) throws SQLException { }
    public void contactComboBox(javafx.event.ActionEvent actionEvent) { }
    public void typeComboBox(javafx.event.ActionEvent actionEvent) { }
    public void monthComboBox(javafx.event.ActionEvent actionEvent) { }

    //Set month & type variables
    String month = null;
    String type = null;

    /**
     * Create Observable list for Months & Types
     * Set items in combo boxes
     */
 public void setComboBoxesTab1() {
     ObservableList<String> Months = FXCollections.observableArrayList("January", "February", "March",
             "April", "May", "June", "July", "August", "September", "October", "November", "December");

     ObservableList<String> Types = FXCollections.observableArrayList("Stand Up", "All Hands", "Demos",
             "Brain Storm");

     tab1Month.setItems(Months);
     tab1Type.setItems(Types);
 }

    /**
     *  Get all contacts from ContactDAO
     *  Create observable list
     *  Get id's from Contact Object & add to Observable list
     *  Set id's in combo box
     * @throws SQLException
     */
    public void setComboBoxesTab3() throws SQLException {
        ObservableList<Contact> allContacts = ContactDAO.getAllContacts();
        ObservableList<Integer> allContactIds = FXCollections.observableArrayList();

        for (Contact contact : allContacts){
            if (contact.getId() != 0){
                int customerId = contact.getId();
                allContactIds.add(customerId);
            }
        }
        tab3Contact.setItems(allContactIds);
    }

    /**
     *  Get all customers from CustomerDAO
     *  Create observable list
     *  Get id's from Customer Object & add to Observable list
     *  Set id's in combo box
     * @throws SQLException
     */
    public void setComboBoxesTab2() throws SQLException {
        ObservableList<Customer> allCustomers = CustomerDAO.getAllCustomers();
        ObservableList<Integer> allCustomerIds = FXCollections.observableArrayList();

        for (Customer c : allCustomers){
            if (c.getId() != 0){
                int customerId = c.getId();
                allCustomerIds.add(customerId);
            }
        }
        tab2Customer.setItems(allCustomerIds);
    }

    /**
     *
     * @param event switch to main menu fxml on click
     * @throws IOException
     */
    public void mainMenuBtn1(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param event switch to main menu fxml on click
     * @throws IOException
     */
    public void mainMenuBtn2(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param event switch to main menu fxml on click
     * @throws IOException
     */
    public void mainMenuBtn3(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param event If month and type selection from combo boxes are not null, get value and add as arguments to method
     *              getAppointmentByMonthAndType.
     *              Populate table using ObservableList.
     * @throws SQLException
     */
    @FXML
    void generateReportActionBtn1(javafx.event.ActionEvent event) throws SQLException {

        if (tab1Month.getSelectionModel().getSelectedItem() != null || tab1Type.getSelectionModel().getSelectedItem() != null) {
            String selectedMonth = tab1Month.getValue();
            String selectedType = tab1Type.getValue();

            ObservableList<ReportObject> appointments = AppointmentDAO.getAppointmentByMonthAndType(selectedMonth, selectedType);
            tab1Table.setItems(appointments);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Complete all fields to generate report");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param event If customer selection from combo box is not null, get value and add as argument to method
     *              getApptByCustomerId.
     *              Populate table using ObservableList.
     * @throws SQLException
     */
    @FXML
    void generateReportActionBtn2(javafx.event.ActionEvent event) throws SQLException {
         if (tab2Customer.getSelectionModel().getSelectedItem() != null) {
             int selectedCustomer = tab2Customer.getValue();
             ObservableList<Appointment> appointments = AppointmentDAO.getApptByCustomerId(selectedCustomer);
             tab2Table.setItems(appointments);
         }
         else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Empty Field");
             alert.setContentText("Must choose a customer to generate report");
             alert.showAndWait();
         }

    }

    /**
     *
     * @param actionEvent If contact selection from combo box is not null, get value and add as argument to method
     *                    getApptByContactId.
     *                    Populate table using ObservableList.
     * @throws SQLException
     */
    @FXML
    public void generateReportActionBtn3(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (tab3Contact.getSelectionModel().getSelectedItem() != null) {
            int selectedContact = tab3Contact.getValue();
            ObservableList<Appointment> contactAppt = AppointmentDAO.getApptByContactId(selectedContact);
            tab3Table.setItems(contactAppt);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Must choose a customer to generate report");
            alert.showAndWait();
        }
    }

    /**
     * Initialize combo boxes
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBoxesTab1();
        try {
            setComboBoxesTab2();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            setComboBoxesTab3();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Get Appointment by Type & Month
        countCol.setCellValueFactory(new PropertyValueFactory<>("Count"));
        MonthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        tab1TypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        // Get Appointment by Customer - Table
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        // Get appointment by Contact - Table
        apptIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol1.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        descCol1.setCellValueFactory(new PropertyValueFactory<>("description"));
        startCol1.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol1.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdCol1.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

}

package C195.Controller;

import C195.Database.ContactDAO;
import C195.Model.Appointment;
import C195.Model.Contact;
import C195.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class ReportsController implements Initializable {

    @FXML
    private Tab tabCountApptTypeMonth;

    @FXML
    private TableColumn<Appointment, Integer> countCol;

    @FXML
    private TableColumn<Appointment, ?> MonthCol;

    @FXML
    private TableColumn<Appointment, ?> tab1TypeCol;

    @FXML
    private ComboBox<String> tab1Type;

    @FXML
    private ComboBox<String> tab1Month;

    @FXML
    private Button tab1MainMenu;

    @FXML
    private Tab tabApptByCustomer;

    @FXML
    private ComboBox<Customer> tab2Customer;

    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;

    @FXML
    private TableColumn<Appointment, ?> titleCol;

    @FXML
    private TableColumn<Appointment, ?> typeCol;

    @FXML
    private TableColumn<Appointment, ?> descCol;

    @FXML
    private TableColumn<Appointment, ?> startCol;

    @FXML
    private TableColumn<Appointment, ?> endCol;

    @FXML
    private TableColumn<Appointment, ?> custIdCol;

    @FXML
    private Button tab2MainMenu;

    @FXML
    private Tab tabApptByContact;

    @FXML
    private TableColumn<Appointment, ?> apptIdCol1;

    @FXML
    private TableColumn<Appointment, ?> titleCol1;

    @FXML
    private TableColumn<Appointment, ?> typeCol1;

    @FXML
    private TableColumn<Appointment, ?> descCol1;

    @FXML
    private TableColumn<Appointment, ?> startCol1;

    @FXML
    private TableColumn<Appointment, ?> endCol1;

    @FXML
    private TableColumn<Appointment, ?> custIdCol1;

    @FXML
    private ComboBox<Contact> tab3Contact;

    @FXML
    private Button tab3MainMenu;
    
 public void setComboBoxes() {
     ObservableList<String> Months = FXCollections.observableArrayList("January", "February", "March",
             "April", "May", "June", "July", "August", "September", "October", "November", "December");

     ObservableList<String> Types = FXCollections.observableArrayList("Stand Up", "All Hands", "Demos",
             "Brain Storm");

     tab1Month.setItems(Months);
     tab1Type.setItems(Types);
 }
    public void customerComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void typeComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void monthComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void mainMenuBtn1(javafx.event.ActionEvent actionEvent) {
    }

    public void mainMenuBtn2(javafx.event.ActionEvent actionEvent) {
    }

    public void contactComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void mainMenuBtn3(javafx.event.ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBoxes();
        try {
            ObservableList<Contact> contacts = ContactDAO.getAllContacts();
            tab3Contact.setItems(contacts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}

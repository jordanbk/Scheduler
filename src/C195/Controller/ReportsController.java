package C195.Controller;

import C195.Database.AppointmentDAO;
import C195.Database.ContactDAO;
import C195.Model.Appointment;
import C195.Model.Contact;
import C195.Model.Customer;
import C195.Model.ReportObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class ReportsController implements Initializable {

    @FXML
    private Tab tabCountApptTypeMonth;

    @FXML
    private TableView<ReportObject> tab1Table;

    @FXML
    private TableView<ReportObject> tab2Table;

    @FXML
    private TableView<ReportObject> tab3Table;

    @FXML
    private Button tab2ReportGen;

    @FXML
    private Button tab1MainMenu;

    @FXML
    private TableColumn<ReportObject, Integer> countCol;

    @FXML
    private TableColumn<ReportObject, String> MonthCol;

    @FXML
    private TableColumn<ReportObject, String> tab1TypeCol;

    @FXML
    private ComboBox<String> tab1Type;

    @FXML
    private ComboBox<String> tab1Month;

    @FXML
    private Tab tabApptByCustomer;

    @FXML
    private ComboBox<Customer> tab2Customer;

    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private TableColumn<Appointment, String> descCol;

    @FXML
    private TableColumn<Appointment, String> startCol;

    @FXML
    private TableColumn<Appointment, String> endCol;

    @FXML
    private TableColumn<Appointment, Integer> custIdCol;

    @FXML
    private Button tab2MainMenu;

    @FXML
    private Tab tabApptByContact;

    @FXML
    private TableColumn<Appointment, Integer> apptIdCol1;

    @FXML
    private TableColumn<Appointment, String> titleCol1;

    @FXML
    private TableColumn<Appointment, String> typeCol1;

    @FXML
    private TableColumn<Appointment, String> descCol1;

    @FXML
    private TableColumn<Appointment, String> startCol1;

    @FXML
    private TableColumn<Appointment, String> endCol1;

    @FXML
    private TableColumn<Appointment, Integer> custIdCol1;

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
    @FXML
    void generateReportActionBtn1(javafx.event.ActionEvent event) throws SQLException {
        String selectedMonth = tab1Month.getSelectionModel().getSelectedItem().toUpperCase();
        String selectedType = tab1Type.getSelectionModel().getSelectedItem();

        tab1Table.setItems(AppointmentDAO.getAppointmentByMonthAndType(selectedMonth, selectedType));
    }
    @FXML
    void generateReportActionBtn2(javafx.event.ActionEvent event) {

    }
    @FXML
    public void generateReportActionBtn3(javafx.event.ActionEvent actionEvent) {
    }

/*    private void loadAppointments() throws Exception {
        try {
            calendarTableMain.setItems(AppointmentDAO.getAllAppointments());
            calendarIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            calTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            calDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            calLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            calContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            calStartTimeDateCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            calEndTimeDateCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            calCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            calTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
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

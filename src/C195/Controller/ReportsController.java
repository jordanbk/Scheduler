package C195.Controller;

import C195.Database.AppointmentDAO;
import C195.Database.ContactDAO;
import C195.Database.CustomerDAO;
import C195.Model.Appointment;
import C195.Model.Contact;
import C195.Model.Customer;
import C195.Model.ReportObject;
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

    String month = null;
    String type = null;

 public void setComboBoxesTab1() {
     ObservableList<String> Months = FXCollections.observableArrayList("January", "February", "March",
             "April", "May", "June", "July", "August", "September", "October", "November", "December");

     ObservableList<String> Types = FXCollections.observableArrayList("Stand Up", "All Hands", "Demos",
             "Brain Storm");

     tab1Month.setItems(Months);
     tab1Type.setItems(Types);
 }

    public void tab2Customer() throws SQLException {
     ObservableList<Customer> allCustomers = CustomerDAO.getAllCustomers();
     tab2Customer.setItems(allCustomers);

    }
    public void setComboBoxesTab3() throws SQLException {

        ObservableList<Contact> allContacts = ContactDAO.getAllContacts();
        tab3Contact.setItems(allContacts);

    }
    public void customerComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void contactComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void typeComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void monthComboBox(javafx.event.ActionEvent actionEvent) {
    }

    public void mainMenuBtn1(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void mainMenuBtn2(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void mainMenuBtn3(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void generateReportActionBtn1(javafx.event.ActionEvent event) throws SQLException {
/*        if (inputIsEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Complete all fields to add appointment");
            alert.showAndWait();
        }
        else {*/
            System.out.println("View month button clicked");
            String selectedMonth = tab1Month.getValue();
            String selectedType = tab1Type.getValue();


        //tab1Table.setItems(AppointmentDAO.getAppointmentByMonthAndType(month, type));
        ObservableList<ReportObject> appointments = AppointmentDAO.getAppointmentByMonthAndType(selectedMonth, selectedType);
        tab1Table.setItems(appointments);

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
        setComboBoxesTab1();

        try {
            setComboBoxesTab3();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        countCol.setCellValueFactory(new PropertyValueFactory<>("Count"));
        MonthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        tab1TypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));

        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("Appointment Id"));
        apptIdCol1.setCellValueFactory(new PropertyValueFactory<>("Appointment Id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        typeCol1.setCellValueFactory(new PropertyValueFactory<>("Type"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        descCol1.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        startCol1.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        endCol1.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer Id"));
        custIdCol1.setCellValueFactory(new PropertyValueFactory<>("Customer Id"));
    }

}

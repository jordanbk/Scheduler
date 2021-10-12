package C195.Controller;

import C195.Database.*;
import C195.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static C195.Controller.CalenderController.getAppointmentSelected;

/**
 *
 * The UpdateApptController is a controller for the UpdateAppointment.fxml
 * @author Jordan Burke
 *
 */
public class UpdateApptController implements Initializable {

    AppointmentDAO appointmentDAO = new AppointmentDAO();
    //private static AppointmentDAO apptSelected;
    ContactDAO contactDAO = new ContactDAO();
    UserDAO userDAO = new UserDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    private Appointment selectedAppointment = null;
    Customer customer;
    Stage stage;
    Parent scene;
    private boolean overlapping;

    private int appointmentID;
    @FXML private ComboBox<Contact> updateApptContact;
    @FXML private TextField updateApptTitle;
    @FXML private TextField updateApptDesc;
    @FXML private TextField updateApptLocation;
    @FXML private DatePicker updateApptStartDate;
    @FXML private ComboBox<LocalTime> updateApptStartTime;
    @FXML private ComboBox<LocalTime> updateApptEndTime;
    @FXML private ComboBox<User> updateApptUser;
    @FXML private ComboBox<Customer> updateApptCustID;
    @FXML private TextField updateApptID;
    @FXML private ComboBox<String> updateApptType;


    @FXML void updateApptContact(ActionEvent event) {

    }

    @FXML
    void updateApptDesc(ActionEvent event) {

    }

    @FXML
    void updateApptEndTime(ActionEvent event) {

    }

    @FXML
    void updateApptID(ActionEvent event) {

    }

    @FXML
    void updateApptLocation(ActionEvent event) {

    }

    @FXML
    void updateApptStartDate(ActionEvent event) {

    }

    @FXML
    void updateApptStartTime(ActionEvent event) {

    }
/*    Integer apptId, String title, String description, String location,
    String type, LocalDateTime start, LocalDateTime end, Integer customerId,
    Integer userId, Integer contactId, String contactName) throws SQLException {

        String insertStatement = "UPDATE appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type," +
                " Start, End, Customer_ID, User_ID)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);

        ps.setInt(1, apptId);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, contactName);
        ps.setString(6, type);
        ps.setTimestamp(7, Timestamp.valueOf(start));
        ps.setTimestamp(8, Timestamp.valueOf(end));
        ps.setInt(9, customerId);
        ps.setInt(10, userId);

        ps.execute();
    }*/
    @FXML
    void updateApptSubmitBtn(ActionEvent event) throws SQLException, IOException {
        //
        if (inputIsEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Complete all fields to add appointment");
            alert.showAndWait();
        }
        else {
            LocalDate selectedDate = updateApptStartDate.getValue();
            LocalTime selectedStart = updateApptStartTime.getValue();
            LocalTime selectedEnd = updateApptEndTime.getValue();
            LocalDateTime startDateTime = LocalDateTime.of(selectedDate, selectedStart);
            LocalDateTime endDateTime = LocalDateTime.of(selectedDate, selectedEnd);

            if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("The time selected is before current time");
                alert.setContentText("Select a time and date in the future.");
                alert.showAndWait();
            }
            else {
                //String id = updateApptID.getId();
                String title = updateApptTitle.getText();
                String description = updateApptDesc.getText();
                String location = updateApptLocation.getText();
                Contact contact = updateApptContact.getSelectionModel().getSelectedItem();
                int customerId = customer.getId();
                String type = updateApptType.getValue();
                Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
                Timestamp endTimestamp = Timestamp.valueOf(endDateTime);
                Customer customer = updateApptCustID.getSelectionModel().getSelectedItem();
                int userId = updateApptUser.getSelectionModel().getSelectedItem().getUserId();


                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../Views/Calendar.fxml"));
                stage.setScene(new Scene(scene));
                stage.setResizable(false);
                stage.show();
            }
        }
    }

    private boolean inputIsEmpty() {
        return (updateApptTitle.getText().isEmpty()
                || updateApptDesc.getText().isEmpty()
                || updateApptLocation.getText().isEmpty()
                || updateApptType.getValue() == null
                || updateApptStartDate.getValue() == null
                || updateApptStartTime.getValue() == null
                || updateApptEndTime.getValue() == null
                || updateApptCustID.getValue() == null
        );
    }

    @FXML
    void updateApptTitle(ActionEvent event) {

    }

    @FXML
    void updateApptType(ActionEvent event) {

    }

    @FXML
    void updateApptUser(ActionEvent event) {

    }

    @FXML
    void updateApptCustID(ActionEvent event) {

    }
    @FXML
    void updateApptCancelBtn(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Cancel Confirmation");
        alert.setContentText("Are you sure you would like to cancel? All changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(getClass().getResource("../Views/Calendar.fxml"));
            Scene scene = new Scene(parent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }
    }
    public User getUsernameFromUser(int id) throws Exception {
        ObservableList<User> users = UserDAO.getAllUsers();
        User userSelected = null;

        for (User u : users) {
            if (id == u.getUserId()) {
                userSelected = u;
            }
        }
        return userSelected;
    }



    public void populateFields(Appointment appointment) throws SQLException {
        selectedAppointment = appointment;
        Contact contactSelected = null;
        Customer customerSelected = null;
        User userSelected = null;

        ObservableList<Contact> displayAllContacts = ContactDAO.getAllContacts();
        ObservableList<User> displayAllUsers = UserDAO.getAllUsers();
        ObservableList<Integer> displayAllCustIds = FXCollections.observableArrayList();
        ObservableList<Customer> displayAllCustomers = CustomerDAO.getAllCustomers();

        for (Contact c : displayAllContacts)
            if (c.getId() == appointment.getId()) {
                contactSelected = c;
            }

        for (Customer c : displayAllCustomers) {
            if (c.getId() == appointment.getId()){
                customerSelected = c;
            }
        }
        for (Customer c : displayAllCustomers){
            displayAllCustIds.add(c.getId());
        }
        for (User u : displayAllUsers) {
                if (u.getUserId() == appointment.getUserId()) {
                    userSelected = u;
                }
        }
/*       for (String t : displayAllTypes) {
            if (t.equals(Appointment.getType())) {
                typeSelected = t;
            }
        }*/

        updateApptID.setText(Integer.toString(selectedAppointment.getId()));
        updateApptTitle.setText(selectedAppointment.getTitle());
        updateApptContact.setItems(displayAllContacts);
        updateApptContact.setValue(contactSelected);
        updateApptCustID.setItems(displayAllCustomers);
        updateApptCustID.setValue(customerSelected);
        updateApptDesc.setText(selectedAppointment.getDescription());
        updateApptLocation.setText(selectedAppointment.getLocation());
        updateApptType.setValue(selectedAppointment.getType());
        updateApptUser.setItems(displayAllUsers);
        updateApptUser.setValue(userSelected);
        updateApptStartDate.setValue(selectedAppointment.getStart().toLocalDate());
        updateApptStartTime.setValue(selectedAppointment.getStart().toLocalTime());
        updateApptEndTime.setValue(selectedAppointment.getEnd().toLocalTime());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //Appointment appointment = Appointment.getApptByID(apptSelected.getApptID());

            ObservableList<Customer> allCustomers = CustomerDAO.getAllCustomers();
            ObservableList<Integer> allCustomerIds = FXCollections.observableArrayList();
            for (Customer c : allCustomers){
                if (c.getId() != 0){
                    int customerId = c.getId();
                    allCustomerIds.add(customerId);
                }
            }
            ObservableList<Contact> contactList = ContactDAO.getAllContacts();
            ObservableList<User> userList = UserDAO.getAllUsers();
            updateApptContact.setItems(contactList);
            //updateApptCustID.getSelectionModel().select(Integer.valueOf(appointment.getCustomerId()));
            updateApptUser.setItems(userList);
            updateApptType.setItems(Appointment.types);
            //updateApptDesc.setText(appointment.getDescription());
            //updateApptLocation.setText(appointment.getLocation());
            //updateApptTitle.setText(appointment.getTitle());
            //updateApptUser.getSelectionModel().select(Integer.valueOf(appointment.getUserId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalTime startEST = LocalTime.of(8, 0);
        LocalTime endEST = LocalTime.of(22, 0);
        LocalTime start = startEST;
        LocalTime end = endEST;

        while (startEST.isBefore(endEST.plusSeconds(1))){
            updateApptStartTime.getItems().add(LocalTime.from(startEST));
            startEST = startEST.plusHours(1);
        }
    }

    @FXML
    public void addApptStartTime(ActionEvent actionEvent) {
        updateApptEndTime.getItems().clear();

        //Decouples Date from ZonedDateTime and uses the selected date as part of localEnd
        LocalTime localEndTime = Time.getLocalEndTime();

        LocalTime selectedStart = updateApptStartTime.getSelectionModel().getSelectedItem();
        LocalTime selectedStartDate = selectedStart;

        updateApptEndTime.setValue(selectedStart.plusHours(1));

        while (selectedStartDate.isBefore(localEndTime.plusSeconds(1))) {
            updateApptEndTime.getItems().add(LocalTime.from(selectedStartDate.plusHours(1)));
            selectedStartDate = selectedStartDate.plusHours(1);
        }
    }

}


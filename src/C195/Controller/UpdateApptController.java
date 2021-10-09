package C195.Controller;

import C195.Database.*;
import C195.Model.*;
import com.sun.javafx.charts.Legend;
import javafx.beans.Observable;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateApptController implements Initializable {

    AppointmentDAO appointmentDAO = new AppointmentDAO();
    ContactDAO contactDAO = new ContactDAO();
    UserDAO userDAO = new UserDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    Stage stage;
    Parent scene;

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
    private Appointment appointment;

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

    @FXML
    void updateApptSubmitBtn(ActionEvent event) throws SQLException, IOException {
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
                String title = updateApptTitle.getText();
                String description = updateApptDesc.getText();
                String location = updateApptLocation.getText();
                Contact contact = updateApptContact.getSelectionModel().getSelectedItem();
                int contactId = contact.getId();
                String type = updateApptType.getValue();
                Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
                Timestamp endTimestamp = Timestamp.valueOf(endDateTime);
                Customer customer = updateApptCustID.getSelectionModel().getSelectedItem();
                int userId = updateApptUser.getSelectionModel().getSelectedItem().getUserId();

                AppointmentDAO.updateAppointment(title, description, location, type, startTimestamp, endTimestamp, customer.getId(), userId, contactId);

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

        for(User u : users){
            if(id == u.getUserId()){
                userSelected = u;
            }
        }
        return userSelected;
    }

    public void populateFields(Appointment apt) throws Exception {

        Contact contactSelected = null;
        Customer customerSelected = null;
        User userSelected = null;
        this.appointment = apt;

        ObservableList<Contact> displayAllContacts = ContactDAO.getAllContacts();
        ObservableList<Customer> displayAllCustomers = CustomerDAO.getAllCustomers();
        ObservableList<User> displayAllUsers = UserDAO.getAllUsers();
        ObservableList<Integer> displayAllCustIds = FXCollections.observableArrayList();
        //ObservableList<String> displayAllTypes = Appointment.getType();


        for (Contact c : displayAllContacts)
            if (c.getId() == apt.getContactId()) {
                contactSelected = c;
            }
        for (Customer c : displayAllCustomers) {
            if (c.getId() == apt.getCustomerId()){
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

        updateApptID.setText(Integer.toString(apt.getId()));
        updateApptTitle.setText(apt.getTitle());
        updateApptContact.setItems(displayAllContacts);
        updateApptContact.setValue(contactSelected);
        updateApptCustID.setItems(displayAllCustomers);
        updateApptCustID.setValue(customerSelected);
        updateApptDesc.setText(apt.getDescription());
        updateApptLocation.setText(apt.getLocation());
        updateApptType.setValue(this.appointment.getType());
        updateApptUser.setItems(displayAllUsers);
        updateApptUser.setValue(userSelected);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
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
            //updateApptCustID.setItems(allCustomerIds);
            updateApptUser.setItems(userList);
            updateApptType.setItems(Appointment.types);

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


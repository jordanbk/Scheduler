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

/**
 *
 * The UpdateApptController is a controller for the UpdateAppointment.fxml
 * @author Jordan Burke
 *
 */
public class UpdateApptController implements Initializable {

    AppointmentDAO appointmentDAO = new AppointmentDAO();
    ContactDAO contactDAO = new ContactDAO();
    UserDAO userDAO = new UserDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    private Appointment selectedAppointment = null;
    Customer customer;
    Stage stage;
    Parent scene;
    private boolean overlapping;

    private int appointmentID;
    @FXML
    private ComboBox<Contact> updateApptContact;
    @FXML
    private TextField updateApptTitle;
    @FXML
    private TextField updateApptDesc;
    @FXML
    private TextField updateApptLocation;
    @FXML
    private DatePicker updateApptStartDate;
    @FXML
    private ComboBox<LocalTime> updateApptStartTime;
    @FXML
    private ComboBox<LocalTime> updateApptEndTime;
    @FXML
    private ComboBox<User> updateApptUser;
    @FXML
    private ComboBox<Customer> updateApptCustID;
    @FXML
    private TextField updateApptID;
    @FXML
    private ComboBox<String> updateApptType;

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
    void updateApptContact(ActionEvent event) {

    }

    @FXML
    void updateApptDesc(ActionEvent event) {

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
    void updateApptEndTime(ActionEvent event) {
        updateApptEndTime.getItems().clear();

    }

    private LocalDateTime createStartLocaleDateTime() {

        LocalDate startDate = updateApptStartDate.getValue();
        LocalTime startTime = updateApptStartTime.getValue();
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        return start;
    }

    private LocalDateTime createEndLocaleDateTime() {

        LocalDate endDate = updateApptStartDate.getValue();
        LocalTime endTime = updateApptEndTime.getValue();
        LocalDateTime end = LocalDateTime.of(endDate, endTime);
        return end;
    }

    @FXML
    void updateApptSubmitBtn(ActionEvent event) throws SQLException, IOException {
/*        updateAppointment(String contactName, String title, String description, String location, String contact, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int apptId)
        int rowsAffected = AppointmentDAO.updateAppointment("Jordan", "Title", "Desc", "loc", "contact", "type", LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1, 1);
        if (rowsAffected > 0) {
            selectedAppointment.setContactName(updateApptTitle.getText());
            selectedAppointment.setTitle(updateApptTitle.getText());
            selectedAppointment.setDescription(updateApptDesc.getText());
            selectedAppointment.setLocation(updateApptLocation.getText());
        System.out.println(rowsAffected);
        }
        */
        if (inputIsEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Complete all fields to add appointment");
            alert.showAndWait();
        } else {
            LocalDate selectedDate = updateApptStartDate.getValue();
            LocalTime selectedStart = updateApptStartTime.getValue();
            LocalTime selectedEnd = updateApptEndTime.getValue();
            LocalDateTime startDateTime = LocalDateTime.of(selectedDate, selectedStart);
            LocalDateTime endDateTime = LocalDateTime.of(selectedDate, selectedEnd);
            System.out.println("startdatetime=" + startDateTime);

            if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("The time selected is before current time");
                alert.setContentText("Select a time and date in the future.");
                alert.showAndWait();
            } else {
                int appId = Integer.parseInt(updateApptID.getText());
                String title = updateApptTitle.getText();
                String description = updateApptDesc.getText();
                String location = updateApptLocation.getText();
                Contact contact = updateApptContact.getSelectionModel().getSelectedItem();
                //int customerId = updateApptCustID.getValue().getId();
                int customerId = ((Customer) updateApptCustID.getValue()).getId();
                String type = updateApptType.getValue();
                LocalDateTime start = createStartLocaleDateTime();
                LocalDateTime end = createEndLocaleDateTime();
                Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
                Timestamp endTimestamp = Timestamp.valueOf(endDateTime);
                Customer customer = updateApptCustID.getSelectionModel().getSelectedItem();
                int userId = updateApptUser.getSelectionModel().getSelectedItem().getUserId();
                //int contactId = updateApptContact.getSelectionModel().getSelectedItem();

                Appointment appointment = new Appointment(appId, title, description, location, type,
                        startDateTime, endDateTime, customerId, userId, contact.getId());
                AppointmentDAO.updateAppointment(appointment);

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

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
            if (c.getId() == appointment.getId()) {
                customerSelected = c;
            }
        }
        for (Customer c : displayAllCustomers) {
            displayAllCustIds.add(c.getId());
        }
        for (User u : displayAllUsers) {
            if (u.getUserId() == appointment.getUserId()) {
                userSelected = u;
            }
        }

        updateApptContact.setItems(displayAllContacts);
        updateApptContact.setValue(contactSelected);

        updateApptCustID.setItems(displayAllCustomers);
        updateApptCustID.setValue(customerSelected);

        updateApptUser.setItems(displayAllUsers);
        updateApptUser.setValue(userSelected);

        updateApptDesc.setText(selectedAppointment.getDescription());
        updateApptLocation.setText(selectedAppointment.getLocation());
        updateApptType.setValue(selectedAppointment.getType());
        updateApptID.setText(Integer.toString(selectedAppointment.getId()));
        updateApptTitle.setText(selectedAppointment.getTitle());
        updateApptStartDate.setValue(selectedAppointment.getStart().toLocalDate());
        updateApptStartTime.setValue(selectedAppointment.getStart().toLocalTime());
        updateApptEndTime.setValue(selectedAppointment.getEnd().toLocalTime());

    }

    private void populateTimeComboBox() {

        LocalTime start = LocalTime.of(0, 0);

        for (int i = 0; i < 100; i++) {
            updateApptStartTime.getItems().add(start);
            updateApptEndTime.getItems().add(start);
            start = start.plusMinutes(15);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       try {
            populateTimeComboBox();
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
            updateApptUser.setItems(userList);
            updateApptType.setItems(Appointment.types);
            updateApptID.setDisable(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        updateApptStartDate.setValue(LocalDate.from(currentDateTime));

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

        LocalTime localEndTime = Time.getLocalEndTime();

        LocalTime selectedStart = updateApptStartTime.getSelectionModel().getSelectedItem();
        LocalTime selectedStartDate = selectedStart;

        updateApptEndTime.setValue(selectedStart);

       while (selectedStartDate.isBefore(localEndTime.plusSeconds(1))) {
           updateApptEndTime.getItems().add(LocalTime.from(selectedStartDate.plusHours(1)));
           selectedStartDate = selectedStartDate.plusHours(1);
       }
    }
}


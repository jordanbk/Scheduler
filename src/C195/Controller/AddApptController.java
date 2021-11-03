package C195.Controller;

import C195.Database.*;
import C195.Model.Appointment;
import C195.Model.Contact;
import C195.Model.Customer;
import C195.Model.User;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class handles the Add Appointment screen
 * @author Jordan Burke
 */
public class AddApptController implements Initializable {
    @FXML private ComboBox<User> addApptUser;
    @FXML private ComboBox<LocalTime> addApptStartTime;
    @FXML private ComboBox<LocalTime> addApptEndTime;
    @FXML private ComboBox<Contact> addApptContact;
    @FXML private ComboBox<String> addApptType;
    @FXML private TextField addApptTitle;
    @FXML private TextField addApptDesc;
    @FXML private TextField addApptLocation;
    @FXML private DatePicker addApptDate;
    @FXML private TextField addApptID;
    @FXML private ComboBox<Integer> addApptCustID;

    @FXML void addApptContact(ActionEvent event) { }
    @FXML void addApptCustID(ActionEvent event) { }
    @FXML void addApptEndTimeA(ActionEvent event) { }
    @FXML void addApptType(ActionEvent event) { }

    AppointmentDAO appointmentDAO = new AppointmentDAO();
    ContactDAO contactDAO = new ContactDAO();
    UserDAO userDAO = new UserDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    Stage stage;
    Parent scene;

    /**
     * Confirmation alert confirms cancellation
     * If button is clicked, adding appointment is cancelled and user is taken back to Calendar.fxml
     * @param event button to cancel the add appointment request.
     * @throws IOException
     */
    @FXML
    void addApptCancelBtn(ActionEvent event) throws IOException {
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

    /**
     * @return start date and start time to localdatetime
     */
    private LocalDateTime generateStartDateTime() {

        LocalDate startDate = addApptDate.getValue();
        LocalTime startTime = addApptStartTime.getValue();
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        return start;
    }

    /**
     * @return end date and time to localdate time
     */
    private LocalDateTime generateEndDateTime() {

        LocalDate endDate = addApptDate.getValue();
        LocalTime endTime = addApptEndTime.getValue();
        LocalDateTime end = LocalDateTime.of(endDate, endTime);
        return end;
    }

    /**
     * Checks for any empty fields
     * Checks for overlapping appointments
     * Adds appointment to the database and returns user to Calendar.fxml
     * @param event button to save appointment details to database
     * @throws Exception
     */
    @FXML
    void addApptSubmitBtn(ActionEvent event) throws Exception {
        if (inputIsEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Complete all fields to add appointment");
            alert.showAndWait();
        }
        else {
            LocalDate selectedDate = addApptDate.getValue();
            LocalTime selectedStart = addApptStartTime.getValue();
            LocalTime selectedEnd = addApptEndTime.getValue();
            LocalDateTime startDateTime = LocalDateTime.of(selectedDate, selectedStart);
            LocalDateTime endDateTime = LocalDateTime.of(selectedDate, selectedEnd);

            if (AppointmentDAO.getOverlappingAppt(startDateTime, endDateTime, addApptCustID.getSelectionModel().getSelectedItem(), -1 )){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Scheduling Error");
                alert.setContentText("This is an overlapping appointment.");
                alert.showAndWait();
            }
            else {

                String title = addApptTitle.getText();
                String description = addApptDesc.getText();
                String location = addApptLocation.getText();
                String type = addApptType.getSelectionModel().getSelectedItem();
                LocalDateTime start = generateStartDateTime();
                LocalDateTime end = generateEndDateTime();
                int customerId = addApptCustID.getSelectionModel().getSelectedItem();
                int userId = addApptUser.getSelectionModel().getSelectedItem().getUserId();
                int contactId = addApptContact.getSelectionModel().getSelectedItem().getId();
                AppointmentDAO.addAppointment(title, description, location, type, start, end, customerId, userId, contactId);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/C195/Views/Calendar.fxml"));
                stage.setScene(new Scene(scene));
                stage.setResizable(false);
                stage.show();
            }
        }
    }

    /**
     * This method adds time in increments of 15 minutes to the combo boxes
     */
    private void populateTimeComboBox() {
        LocalTime localTimeEnd = Time.getLocalEndTime();
        LocalTime selectedStart = Time.getLocalStartTime();
        LocalTime selectedStartDate = selectedStart;

        while (selectedStartDate.isBefore(localTimeEnd.minusSeconds(1))) {
            addApptStartTime.getItems().add(LocalTime.from(selectedStartDate));
            selectedStartDate = selectedStartDate.plusMinutes(15);
        }
        addApptEndTime.setPromptText("Choose Start First");
    }

    /**
     * This method checks for empty fields
     * @return
     */
    public boolean inputIsEmpty(){
        return (addApptTitle.getText().isEmpty()
                || addApptDesc.getText().isEmpty()
                || addApptLocation.getText().isEmpty()
                || addApptType.getValue() == null
                || addApptDate.getValue() == null
                || addApptStartTime.getValue() == null
                || addApptEndTime.getValue() == null
                || addApptCustID.getValue() == null
        );
    }

    /**
     * Initializes the add appointment screen and populates the table, combo boxes and time conversions
     * @param url
     * @param resourceBundle
     */
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
            addApptContact.setItems(contactList);
            addApptCustID.setItems(allCustomerIds);
            addApptUser.setItems(userList);
            addApptType.setItems(Appointment.types);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method handles time conversion and increments time in combo boxes
     * @param actionEvent
     */
    @FXML
    public void addApptStartTimeA(ActionEvent actionEvent) {
        addApptEndTime.getItems().clear();

        LocalTime localTimeEnd = Time.getLocalEndTime();
        LocalTime selectedStart = addApptStartTime.getSelectionModel().getSelectedItem();
        LocalTime selectedStartDate = selectedStart.plusMinutes(15);

        while (selectedStartDate.isBefore(localTimeEnd.plusSeconds(1))) {
            addApptEndTime.getItems().add(LocalTime.from(selectedStartDate));
            selectedStartDate = selectedStartDate.plusMinutes(15);
        }
        addApptEndTime.getSelectionModel().selectFirst();
    }

}

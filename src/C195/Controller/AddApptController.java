package C195.Controller;

import C195.Database.*;
import C195.Model.Appointment;
import C195.Model.Contact;
import C195.Model.Customer;
import C195.Model.User;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

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

    AppointmentDAO appointmentDAO = new AppointmentDAO();
    ContactDAO contactDAO = new ContactDAO();
    UserDAO userDAO = new UserDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    Stage stage;
    Parent scene;
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

    @FXML
    void addApptContact(ActionEvent event) {

    }

    @FXML
    void addApptCustomer(ActionEvent event) {

    }
    private LocalDateTime createStartLocaleDateTime() {

        LocalDate startDate = addApptDate.getValue();
        LocalTime startTime = addApptStartTime.getValue();
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        return start;
    }

    private LocalDateTime createEndLocaleDateTime() {

        LocalDate endDate = addApptDate.getValue();
        LocalTime endTime = addApptEndTime.getValue();
        LocalDateTime end = LocalDateTime.of(endDate, endTime);
        return end;
    }
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

            if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("The time selected is before current time");
                alert.setContentText("Select a time and date in the future.");
                alert.showAndWait();
            }

            else {

                int apptId = Integer.parseInt(addApptID.getText());
                String title = addApptTitle.getText();
                String description = addApptDesc.getText();
                String location = addApptLocation.getText();
                //Contact contact = addApptContact.getValue();
                String type = addApptType.getSelectionModel().getSelectedItem();
                LocalDateTime start = createStartLocaleDateTime();
                LocalDateTime end = createEndLocaleDateTime();
                Integer customerId = addApptCustID.getSelectionModel().getSelectedItem();
                int userId = addApptUser.getSelectionModel().getSelectedItem().getUserId();
                int contactId = addApptContact.getSelectionModel().getSelectedItem().getId();
                AppointmentDAO.addAppointment(title, description, location, type, start, end, customerId, userId, contactId, apptId);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("C195/Views/Calendar.fxml"));
                stage.setScene(new Scene(scene));
                stage.setResizable(false);
                stage.show();
            }
        }
    }


    @FXML
    public void comboBoxes(){

    }

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

    @FXML
    void addApptCustID(ActionEvent event) {

    }

    @FXML
    void addApptEndTime(ActionEvent event) {
        addApptEndTime.getItems().clear();

        //Decouples Date from ZonedDateTime and uses the selected date as part of localEnd
        LocalTime localEndTime = Time.getLocalEndTime();

        LocalTime selectedStart = addApptStartTime.getSelectionModel().getSelectedItem();
        LocalTime selectedStartDate = selectedStart;

        addApptEndTime.setValue(selectedStart.plusHours(1));

        while (selectedStartDate.isBefore(localEndTime.plusSeconds(1))) {
            addApptEndTime.getItems().add(LocalTime.from(selectedStartDate.plusHours(1)));
            selectedStartDate = selectedStartDate.plusHours(1);
        }
    }


    @FXML
    void addApptType(ActionEvent event) {

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
            addApptContact.setItems(contactList);
            addApptCustID.setItems(allCustomerIds);
            addApptUser.setItems(userList);
            addApptType.setItems(Appointment.types);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalTime startEST = LocalTime.of(8, 0);
        LocalTime endEST = LocalTime.of(22, 0);
        LocalTime start = startEST;
        LocalTime end = endEST;

        while (startEST.isBefore(endEST.plusSeconds(1))){
            addApptStartTime.getItems().add(LocalTime.from(startEST));
            startEST = startEST.plusHours(1);
        }
    }
    @FXML
    public void addApptStartTime(ActionEvent actionEvent) {
        addApptEndTime.getItems().clear();

        //Decouples Date from ZonedDateTime and uses the selected date as part of localEnd
        LocalTime localEndTime = Time.getLocalEndTime();

        LocalTime selectedStart = addApptStartTime.getSelectionModel().getSelectedItem();
        LocalTime selectedStartDate = selectedStart;

        addApptEndTime.setValue(selectedStart.plusHours(1));

        while (selectedStartDate.isBefore(localEndTime.plusSeconds(1))) {
            addApptEndTime.getItems().add(LocalTime.from(selectedStartDate.plusHours(1)));
            selectedStartDate = selectedStartDate.plusHours(1);
        }
    }
    public void addApptUser(ActionEvent actionEvent) {
    }

}

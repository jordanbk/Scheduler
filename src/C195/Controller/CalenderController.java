package C195.Controller;

import C195.Database.AppointmentDAO;
import C195.Database.DatabaseConnection;
import C195.Database.UserDAO;
import C195.Model.Appointment;
import C195.Model.Customer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class CalenderController implements Initializable {
    Stage stage;
    Parent root;
    @FXML private TableColumn<Appointment, Integer> calendarIDCol;
    @FXML private TableColumn<Appointment, String> calTitleCol;
    @FXML private TableColumn<Appointment, String> calDescriptionCol;
    @FXML private TableColumn<Appointment, String> calLocationCol;
    @FXML private TableColumn<Appointment, String> calContactCol;
    @FXML private TableColumn<Appointment, String> calStartTimeDateCol;
    @FXML private TableColumn<Appointment, String> calEndTimeDateCol;
    @FXML private TableColumn<Appointment, String> calCustomerNameCol;
    @FXML private TableColumn<Appointment, Integer> calCustIDCol;
    @FXML private TableColumn<Appointment, String> calTypeCol;
    @FXML private TableView<Appointment> calendarTableMain;
    static ObservableList<Appointment> appointments;
    private static Appointment appointmentSelected = null;

    @FXML
    public void calAddApptBtn(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Views/AddAppointment.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public static Appointment getAppointmentSelected(){
        return appointmentSelected;
    }

    @FXML
    public void calDeleteApptBtn(ActionEvent event) throws Exception {
        if (calendarTableMain.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Continuing will delete the selected appointment.");
            alert.setHeaderText("Do you wish to continue?");
            alert.setTitle("Delete Appointment");

            Optional<ButtonType> result =  alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                try {
                    Appointment selectedApt = calendarTableMain.getSelectionModel().getSelectedItem();
                    AppointmentDAO.deleteAppointment(selectedApt.getId());

                    appointments = AppointmentDAO.getAllAppointments();
                    calendarTableMain.setItems(appointments);
                    calendarTableMain.refresh();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } else { //Displays error message if no appointment is selected to be deleted
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Appointment Selection Error");
            errorAlert.setHeaderText("No appointment selected");
            errorAlert.setContentText("You must select an appointment to delete");
            errorAlert.showAndWait();
        }
    }

    @FXML
    public void calEditApptBtn(ActionEvent event) throws Exception {
        appointmentSelected = calendarTableMain.getSelectionModel().getSelectedItem();
        if (appointmentSelected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/UpdateAppointment.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
/*            Appointment appt = calendarTableMain.getSelectionModel().getSelectedItem();
            UpdateApptController controller = loader.getController();
            controller.populateFields(appt);*/
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No appointment selected");
            alert.setHeaderText("Please select an appointment");
            alert.setContentText("You must select an appointment to edit");
            alert.showAndWait();
        }
    }

    @FXML
    public void calMainMenuBtn(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void calReportBtn(ActionEvent event) {

    }

    @FXML
    public void filterAll(ActionEvent event) {
        try {
            ObservableList<Appointment> allAppts = AppointmentDAO.getAllAppointments();
/*            ObservableList<Appointment> pendingAppointments = FXCollections.observableArrayList();

            for (Appointment appts : allAppts) {
                if (appts.getEnd().isAfter(LocalDateTime.now())) {
                    pendingAppointments.add(appts);
                }
            }*/

            calendarTableMain.setItems(allAppts);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void filterWeek(ActionEvent event) throws Exception {

        calendarTableMain.setItems(AppointmentDAO.getAllAppointments().filtered(a -> a.getStartTime().isAfter
                (LocalDateTime.now()) && a.getStartTime().isBefore(LocalDateTime.now().plusWeeks(1))));
    }

    @FXML
    public void filterMonth(ActionEvent event) throws Exception {
        calendarTableMain.setItems(AppointmentDAO.getAllAppointments().filtered(a -> a.getStartTime().getMonth()
                == LocalDateTime.now().getMonth()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            loadAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadAppointments() throws Exception {
        try {
            calendarTableMain.setItems(AppointmentDAO.getAllAppointments());
            calendarIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            calTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            calDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            calLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            calContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            calStartTimeDateCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            calEndTimeDateCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            calCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            calTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}


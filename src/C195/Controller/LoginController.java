package C195.Controller;

import C195.Database.AppointmentDAO;
import C195.Database.DatabaseConnection;
import C195.Model.Appointment;
import C195.Model.User;
import C195.Utils.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Parent parent;
    @FXML private PasswordField passwordTxt;
    @FXML private TextField usernameTxt;
    @FXML private Label loginMessageLabel;
    @FXML private Label zone;
    @FXML private Label loginPassword;
    private ResourceBundle myBundleTranslator = ResourceBundle.getBundle("Bundle/lang");
    @Override
    public void initialize(URL url, ResourceBundle resourcebundle){
        loginMessageLabel.setText(myBundleTranslator.getString("USERNAME"));
        System.out.println(ZoneId.systemDefault());
        zone.setText(ZoneId.systemDefault().toString());
        loginPassword.setText("Password");
        loginMessageLabel.setText("");
    }


    @FXML
    public void handleLogin(ActionEvent event) throws IOException, SQLException {
        Parent root;
        Stage stage;
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        loginMessageLabel.setText("You tried to login");
        boolean login = LoginController.validateLogin(username, password);
        Logger.AuditLogger(username, login);

        try {
            if (!validateLogin(username, password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(myBundleTranslator.getString("USERNAME") + "Incorrect Username and/or Password");
                alert.setContentText("Enter valid Username and Password");
                Optional<ButtonType> result = alert.showAndWait();

            } if (getApptsIn15Minutes().size() >= 1){
                for (Appointment appt : getApptsIn15Minutes()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upcoming Appointments");
                    alert.setHeaderText("You have an appointment in 15 Minutes");
                    alert.setContentText("Appointment to start in 15 Minutes:" + appt.getTitle() + appt.getId() +" Time: " + appt.getStart());
                }
            }

            else {
                root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
                stage = (Stage) usernameTxt.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } catch(NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean validateLogin(String username, String password) throws SQLException {

        try{
            DatabaseConnection.openConnection();
            String searchStatement = "SELECT * FROM users WHERE User_Name=? AND Password=?";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(searchStatement);

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Appointment> getApptsIn15Minutes() throws Exception {

        ObservableList<Appointment> appointmentList = AppointmentDAO.getAllAppointments();
        ObservableList<Appointment> approachingAppointments = FXCollections.observableArrayList();
        if (appointmentList!= null){

            for (Appointment a : appointmentList){
                LocalDateTime apptStart = a.getStart();
                LocalDateTime currentTime = Timestamp.from(Instant.now()).toLocalDateTime();

                if (apptStart.isAfter(currentTime)) {
                    if (apptStart.isBefore(currentTime.plusMinutes(15))){
                        approachingAppointments.add(a);
                    }
                }
            }
        }
        return approachingAppointments;
    }

}

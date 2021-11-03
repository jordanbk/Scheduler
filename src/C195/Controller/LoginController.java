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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * This class handles the Login screen
 * @author Jordan Burke
 */
public class LoginController implements Initializable {
    Parent parent;
    ResourceBundle myBundleTranslator;
    @FXML private PasswordField passwordTxt;
    @FXML private TextField usernameTxt;
    @FXML private Label loginMessageLabel;
    @FXML private Label username;
    @FXML private Label zone;
    @FXML private Label loginPassword;
    @FXML private Label zoneID;
    @FXML private Button loginBtn;

    /**
     * This method initializes data
     * Sets language to english or french based on users Language
     * Displays users location
     */
    @Override
    public void initialize(URL url, ResourceBundle resourcebundle){
        myBundleTranslator = ResourceBundle.getBundle("Bundle/lang_" + Locale.getDefault().getLanguage());
        username.setText(myBundleTranslator.getString("Username"));
        System.out.println(ZoneId.systemDefault());
        zone.setText(ZoneId.systemDefault().toString());
        loginPassword.setText(myBundleTranslator.getString("Password"));
        zoneID.setText(myBundleTranslator.getString("ZoneID"));
        loginMessageLabel.setText("");
        loginBtn.setText(myBundleTranslator.getString("LoginBtn"));
    }

    /**
     *  Gets username and password and passes them as arguments to the validatelogin method
     *  Reports login activity
     *  Alerts user if username or password is incorrect and checks if user has an upcoming appointment (within 15 minutes)
     *  using the
     *  Alerts user whether or not they have an appointment in 15 minutes
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void handleLogin(ActionEvent event) throws Exception {
        Parent root;
        Stage stage;
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        loginMessageLabel.setText("You tried to login");
        boolean login = LoginController.validateLogin(username, password);
        Logger.AuditLogger(username, login);

        if (!login) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(myBundleTranslator.getString("USERNAME"));
            alert.setContentText(myBundleTranslator.getString("INVALIDLOGIN"));
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        ObservableList<Appointment> fifteen = getApptsIn15Minutes();

        try {
            Locale.setDefault(new Locale("en"));

            stage = (Stage) usernameTxt.getScene().getWindow();
            stage.hide();
             if (fifteen.size() >= 1) {
                 for (Appointment appt : fifteen) {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle(myBundleTranslator.getString("WELCOME"));
                     alert.setContentText(myBundleTranslator.getString("AppointmentInFifteen") + " | " + myBundleTranslator.getString("AppointmentID") + appt.getId() + " | " + myBundleTranslator.getString("Start") + appt.getStart());
                     alert.showAndWait();
                 }
             }
             else{
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle(myBundleTranslator.getString("WELCOME"));
                 alert.setContentText(myBundleTranslator.getString("NoAppointments"));
                 alert.showAndWait();
             }

                root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


        } catch(NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Checks database for username and password
     * @param username
     * @param password
     * @return boolean value based on username and password database check
     * @throws SQLException alerts user if thrown
     */
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

    /**
     *
     * @return
     * @throws Exception
     */
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

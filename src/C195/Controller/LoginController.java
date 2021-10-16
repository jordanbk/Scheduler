package C195.Controller;

import C195.Database.DatabaseConnection;
import C195.Model.User;
import C195.Utils.Logger;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    //public static User user = new User();

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle){
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

        if (!validateLogin(username, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect Username and/or Password");
            alert.setContentText("Enter valid Username and Password");
            Optional<ButtonType> result = alert.showAndWait();

        } else {

            root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
            stage = (Stage) usernameTxt.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

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

}

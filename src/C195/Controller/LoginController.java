package C195.Controller;

import C195.Database.DatabaseConnection;
import C195.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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
        //int userID = getUserID(username);
        //User user = new User();

        if(validateLogin(username, password)){
        int UserID = getUserID(username);

            root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
            stage = (Stage) usernameTxt.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Incorrect Username and/or Password");
            alert.setContentText("Enter valid Username and Password");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    public boolean validateLogin(String username, String password) throws SQLException {

        try{
            DatabaseConnection.openConnection();
            PreparedStatement pst = DatabaseConnection.conn.prepareStatement("SELECT * FROM users WHERE User_Name=? AND Password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
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


    private int getUserID(String username) throws SQLException {
        int userID = -1;

        //create statement object
        Statement statement = DatabaseConnection.conn.createStatement();

        //write SQL statement
        String sqlStatement = "SELECT User_ID FROM users WHERE User_Name ='" + username + "'";

        //create resultset object
        ResultSet result = statement.executeQuery(sqlStatement);

        while (result.next()) {
            userID = result.getInt("User_ID");
        }
        return userID;
    }

}

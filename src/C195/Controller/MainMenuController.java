package C195.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

/**
 * This class handles the Main Menu screen
 * @author Jordan Burke
 */
public class MainMenuController implements Initializable {
    @FXML
    private Button handleCustomers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * This method brings the user to the Calendar screen
     * @param event button to Calendar.fxml
     * @throws IOException
     */
    @FXML
    public void handleAppointments(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/Calendar.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * This method brings the user to the Customer screen
     * @param event button to Customers.fxml
     * @throws IOException
     */
    @FXML
    public void handleCustomers(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * This method brings the user to the Reports screen
     * @param event button to Reports.fxml
     * @throws IOException
     */
    @FXML
    void handleReports(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/Reports.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}

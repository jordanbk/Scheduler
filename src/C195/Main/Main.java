package C195.Main;

import C195.Database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

/**This is the Main method * @author Jordan Burke*/
public class Main extends Application {
    /**
     * This method sets up the JavaFX stage
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        stage.setTitle("Appointment Scheduler");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * This method establishes connection to the database
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //The line below is for testing in French
        //Locale.setDefault(new Locale("fr"));
        DatabaseConnection.openConnection();
        launch(args);
        DatabaseConnection.closeConnection();
    }


}

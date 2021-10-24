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

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        Locale.setDefault(new Locale("fr"));
        DatabaseConnection.openConnection();
        launch(args);
        DatabaseConnection.closeConnection();
    }


}

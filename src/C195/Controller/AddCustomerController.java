package C195.Controller;

import C195.Database.CountryDAO;
import C195.Database.DivisionDAO;
import C195.Model.Country;
import C195.Model.Customer;
import C195.Model.Division;
import com.mysql.cj.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import C195.Database.CustomerDAO;
import C195.Database.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private TextField addCustAddress;
    @FXML private TextField addCustZip;
    @FXML private TextField addCustPhone;
    @FXML private TextField addCustName;
    @FXML private ComboBox<Division> addCustState;
    @FXML private ComboBox<Country> addCustCountry;
    @FXML private Label customerID;

    private ObservableList<String> state = FXCollections.observableArrayList(
            "New York","Washington DC","Miami","Dallas","Phoenix","Los Angeles","London","Liverpool","Manchester","Oxford");

    private ObservableList<Customer> addCustomer = FXCollections.observableArrayList();

    Customer newCustomer;


    public void addCustSubmitBtn(ActionEvent actionEvent) throws IOException, SQLException {
        if (addCustName.getText().isEmpty() || addCustPhone.getText().isEmpty() || addCustAddress.getText().isEmpty()
                || addCustCountry.getSelectionModel().getSelectedItem() == null
                || addCustState.getSelectionModel().getSelectedItem() == null)

        {  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
            alert.setTitle("Empty Fields");
            alert.setContentText("All fields must be filled in");
        }
        else {
            CustomerDAO addCustomerDAO = new CustomerDAO();

            String customerName = addCustName.getText();
            String customerAddress = addCustAddress.getText();
            String customerZip = addCustZip.getText();
            String customerPhone = addCustPhone.getText();
            //String customerCountry = addCustCountry.getValue();
            int customerDivisionID = addCustState.getValue().getDivisionID();

            addCustomerDAO.addCustomer(customerName, customerAddress, customerZip, customerPhone, customerDivisionID);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../Views/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.setResizable(false);
            stage.show();
        }
    }

    public void addCustCancelBtn(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Cancel Confirmation");
        alert.setContentText("Are you sure you would like to cancel? All changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(getClass().getResource("../Views/Customers.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryDAO countryDao = new CountryDAO();

        try {
            //populating Country ComboBox with data
            addCustCountry.setItems(countryDao.getAllCountries());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ObservableList<Division> filterDivisions = FXCollections.observableArrayList();

        addCustCountry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                filterDivisions.clear();

                Country country = addCustCountry.getValue();
                try {
                    for (Division d: DivisionDAO.getAllDivisions()){
                        if(d.getCountryID() == country.getCountryID()){
                            filterDivisions.add(d);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        DivisionDAO stateDAO = new DivisionDAO();
        try {
            addCustState.setItems(filterDivisions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

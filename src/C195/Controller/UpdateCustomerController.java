package C195.Controller;

import C195.Database.CountryDAO;
import C195.Database.CustomerDAO;
import C195.Database.DivisionDAO;
import C195.Model.Country;
import C195.Model.Customer;
import C195.Model.Division;
import javafx.beans.Observable;
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
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the Update Customer screen
 * @author Jordan Burke
 */
public class UpdateCustomerController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML private TextField updateCustAddress;
    @FXML private TextField updateCustZip;
    @FXML private TextField updateCustPhone;
    @FXML private TextField updateCustName;
    @FXML private ComboBox<Division> updateCustState;
    @FXML private ComboBox<Country> updateCustCountry;
    @FXML private TextField updateCustID;
    public void stateOnAction(ActionEvent actionEvent) { }
    private Customer customer = null;

    /**
     * This method gets the customer's division
     * @param id customer ID
     * @return division affiliated with customer
     * @throws Exception
     */
    public Division getDivisionFromCustomer(int id) throws Exception {
        ObservableList<Division> divisions = DivisionDAO.getAllDivisions();
        Division divisionSelected = null;

        for (Division d : divisions){
            if (id == d.getDivisionID()){
                divisionSelected = d;
            }
        }
        return divisionSelected;
    }

    /**
     * This method gets customer's country
     * @param id customer ID
     * @return country affiliated with customer
     * @throws Exception
     */
    public Country getCountryFromCustomer(int id) throws Exception {
        ObservableList<Country> countries = CountryDAO.getAllCountries();
        Country countrySelected = null;

        for(Country c : countries){
            if(id == c.getCountryID()){
                countrySelected = c;
            }
        }
        return countrySelected;
    }

    /**
     * This method populates the fields with the Customer's data
     * @param selectedCustomer customer selected by user
     * @throws Exception
     */
    public void populateCustomer(Customer selectedCustomer) throws Exception {
        customer = selectedCustomer;
        Division divisionSelected = getDivisionFromCustomer(customer.getDivisionID());
        Country countrySelected = getCountryFromCustomer(divisionSelected.getCountryID());

        ObservableList<Division> selectedCountryDiv = FXCollections.observableArrayList();

        try {
            updateCustCountry.setItems(CountryDAO.getAllCountries());
        } catch(SQLException e){
            e.printStackTrace();
        }

        updateCustCountry.setValue(countrySelected);

        for (Division d : DivisionDAO.getAllDivisions()){
            if (countrySelected.getCountryID() == d.getCountryID()){
                selectedCountryDiv.add(d);
            }
        }

        updateCustState.setItems(selectedCountryDiv);
        updateCustState.setValue(divisionSelected);
        updateCustID.setText(Integer.toString(customer.getId()));
        updateCustName.setText(customer.getName());
        updateCustAddress.setText(customer.getAddress());
        updateCustZip.setText(customer.getPostalCode());
        updateCustPhone.setText(customer.getPhone());
    }

    /**
     * This method saves the data from the fields and adds it to the database and brings user to Customer screen
     * @param actionEvent button to update customer
     * @throws SQLException
     * @throws IOException
     * Displays alert if fields are empty
     */
    public void updateCustSubmitBtn(ActionEvent actionEvent) throws SQLException, IOException {
        if (updateCustName.getText().isEmpty() || updateCustPhone.getText().isEmpty() || updateCustAddress.getText().isEmpty()
                || updateCustCountry.getSelectionModel().getSelectedItem() == null
                || updateCustState.getSelectionModel().getSelectedItem() == null)

        {  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
            alert.setTitle("Empty Fields");
            alert.setContentText("All fields must be filled in");
        }
        else {
            CustomerDAO updateCustomerDAO = new CustomerDAO();

            int id = Integer.parseInt(updateCustID.getText());
            String customerName = updateCustName.getText();
            String customerAddress = updateCustAddress.getText();
            String customerZip = updateCustZip.getText();
            String customerPhone = updateCustPhone.getText();
            int customerDivisionID = updateCustState.getValue().getDivisionID();

            updateCustomerDAO.updateCustomer(id, customerName, customerAddress, customerZip, customerPhone, customerDivisionID);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../Views/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * This method cancels the customer update and brings user to Customers screen
     * @param event button to cancel update
     * @throws IOException
     * Displays alert to confirm update
     */
    public void updateCustCancelBtn(ActionEvent event) throws IOException {
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

    private static ObservableList<Division> filterDivisions = FXCollections.observableArrayList();


    /**
     *  Get country id's
     *  Load country id's in combo box
     * @param countryID country ID
     */
    public void loadDivisions(int countryID){
    filterDivisions = FXCollections.observableArrayList();
        try {
            for (Division div: DivisionDAO.getAllDivisions()){
                if(div.getCountryID() == countryID){
                    filterDivisions.add(div);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActionCountry(ActionEvent actionEvent) {
        Country country = updateCustCountry.getSelectionModel().getSelectedItem();
        loadDivisions(country.getCountryID());
        updateCustState.setItems(filterDivisions);

    }

    public static ObservableList<Division> getDivisionList(){
        return filterDivisions;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Division divisionSelected = null;
        Country countrySelected = null;
        try {
            divisionSelected = getDivisionFromCustomer(customer.getDivisionID());
            countrySelected = getCountryFromCustomer(divisionSelected.getCountryID());
            loadDivisions(countrySelected.getCountryID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        DivisionDAO stateDAO = new DivisionDAO();
        try {
            updateCustState.setItems(filterDivisions);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

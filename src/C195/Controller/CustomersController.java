package C195.Controller;

import C195.Database.CustomerDAO;
import C195.Database.DatabaseConnection;
import C195.Model.Customer;
import com.sun.jdi.connect.spi.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CustomersController implements Initializable {
    @FXML private TableColumn<Customer, Integer> customersIdCol;
    @FXML private TableColumn<Customer, String> customersNameCol;
    @FXML private TableColumn<Customer, String> customersAddressCol;
    @FXML private TableColumn<Customer, Integer> customersZipCol;
    @FXML private TableColumn<Customer, Integer> customersPhoneCol;
    @FXML private TableColumn<Customer, Integer> customersDivCol;
    @FXML private TableView<Customer> customerTable;

    ObservableList<Customer> customerTableView = FXCollections.observableArrayList();


    @FXML
    public void customersAddCustBtn(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/AddCustomer.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void customersDeleteCustBtn(ActionEvent event) throws Exception {
        Customer deleteCustomer = customerTable.getSelectionModel().getSelectedItem();
        String name = deleteCustomer.getName();
        int id = deleteCustomer.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Delete Customer");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Are you sure you want to delete " + name + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK){
                Customer customerSelected = customerTable.getSelectionModel().getSelectedItem();

                //removes customer
                CustomerDAO.deleteCustomer(customerSelected.getId());

                //refreshes table
                customerTable.setItems(CustomerDAO.getAllCustomers());

                Alert alerted = new Alert(Alert.AlertType.INFORMATION);
                alerted.setTitle("Customer Deleted");
                alerted.setHeaderText("This customer was successfully deleted:");
                alerted.setContentText("ID: " + customerSelected.getId() + "\n");
                alerted.showAndWait();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Customer Selection Error");
                errorAlert.setHeaderText("No Customer Selected");
                errorAlert.setContentText("Please Select a Customer to Delete");
                errorAlert.showAndWait();
            }

    }

    @FXML
    public void customersEditCustBtn(ActionEvent event) throws Exception {
        if (customerTable.getSelectionModel().getSelectedItem() != null){
            Stage stage;
            Parent root;
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UpdateCustomer.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            UpdateCustomerController controller = loader.getController();
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            controller.populateCustomer(customer);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Selection Error");
            alert.setHeaderText("No Customer Selected For Modification");
            alert.setContentText("Please Select a Customer to Update");
            alert.showAndWait();
        }
    }

    @FXML
    public void customersMainMenuBtn(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void customersReportBtn(ActionEvent event) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerTable.setItems(CustomerDAO.getAllCustomers());
        } catch (Exception e) {
            e.printStackTrace();
        }
        customersIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        customersNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customersAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customersZipCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customersPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customersDivCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        //add customer
        //division model & country model
        //the key to keys (forget about generating keys) look at insert(add) section,
    }

}


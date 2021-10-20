package C195.Database;

import C195.Model.Contact;
import C195.Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {
    /**
     *     Retrieve all contact details from the database by connecting to the database
     *     and using sql to select all values from contacts table
     * @return
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {

        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String selectStatement = "SELECT * FROM contacts";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(selectStatement);

        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contact contact = new Contact(id, name, email);
            allContacts.add(contact);
        }

        return allContacts;
    }
}

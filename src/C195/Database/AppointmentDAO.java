package C195.Database;

import C195.Model.Appointment;
import C195.Model.Appointment;
import C195.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentDAO {

    public static ObservableList<Appointment> getAllAppointments() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        DatabaseConnection.openConnection();
        String sqlStatement = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, customers.Customer_ID, users.User_ID, contacts.Contact_ID, " +
                "contacts.Contact_Name FROM appointments, customers, users, contacts WHERE appointments.Customer_ID = customers.Customer_ID AND " +
                "appointments.User_ID = users.User_ID AND appointments.Contact_ID = contacts.Contact_ID";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sqlStatement);
        ResultSet result = ps.executeQuery();

        while (result.next()){
            int id = result.getInt("Appointment_ID");
            int customerId = result.getInt("Customer_ID");
            int contactId = result.getInt("Contact_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String contactName = result.getString("Contact_Name");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            //LocalDate createDate = result.getDate("Create_Date").toLocalDate();
            //String createdBy = result.getString("Created_By");
            int userId = result.getInt("User_ID");

            Appointment appointmentResult = new Appointment(id, title, description, location, type, start, end, customerId, userId, contactId, contactName);
            allAppointments.add(appointmentResult);
        }
        return allAppointments;
    }

    public static void deleteAppointment(int appointmentId) throws SQLException {
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(deleteStatement);
        ps.setInt(1, appointmentId);

        ps.execute();
    }

    public static void addAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end, Integer customerId, int userId, int contactId) throws SQLException {

        String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);

        ps.execute();
    }

    public static void updateAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end, Integer customerId, int userId, int contactId) throws SQLException {

        String insertStatement = "UPDATE appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);

        ps.execute();
    }

}

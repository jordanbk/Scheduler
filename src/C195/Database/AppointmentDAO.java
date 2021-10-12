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

    public static void addAppointment(String title, String description, String location,
                                      String type, LocalDateTime start, LocalDateTime end, Integer customerId,
                                      Integer userId, String contactName) throws SQLException {

        String insertStatement = "UPDATE appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type," +
                " Start, End, Customer_ID, User_ID)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, contactName);
        ps.setString(5, type);
        ps.setTimestamp(6, Timestamp.valueOf(start));
        ps.setTimestamp(7, Timestamp.valueOf(end));
        ps.setInt(8, customerId);
        ps.setInt(9, userId);

        ps.execute();
    }
/*    public static Appointment getApptByID(int apptID) throws SQLException {
        String insertStatement = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Appointment_ID=?";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);

        ps.setInt(1, apptID);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                Appointment newAppt = new Appointment(
                  rs.getInt("Appointment_ID"),
                  rs.getString("Title"),
                  rs.getString("Description"),
                  rs.getString("Location"),
                  rs.getString("Type"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getDate("End").toLocalDate(),
                        rs.getTimestamp("End").toLocalDateTime(),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name")
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }*/
    public static void updateAppointment(Integer apptId, String title, String description, String location,
                                         String type, LocalDateTime start, LocalDateTime end, Integer customerId,
                                         Integer userId, Integer contactId, String contactName) throws SQLException {

        String insertStatement = "UPDATE appointments SET Appointment_ID = ?, Title = ?, Description = ?, " +
                "Location = ?, Contact_Name = ?, Type = ?, Start = ?, End = ? " +
                "Customer_ID = ?, User_ID = ? WHERE Appointment_ID = ?";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);

        ps.setInt(1, apptId);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, contactName);
        ps.setString(6, type);
        ps.setTimestamp(7, Timestamp.valueOf(start));
        ps.setTimestamp(8, Timestamp.valueOf(end));
        ps.setInt(9, customerId);
        ps.setInt(10, userId);

        ps.execute();
    }


}

package C195.Database;

import C195.Model.Appointment;
import C195.Model.ReportObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDAO {

    public static ObservableList<Appointment> getAllAppointments() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        DatabaseConnection.openConnection();
        String sqlStatement = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM appointments ";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sqlStatement);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            int id = result.getInt("Appointment_ID");
            //int contactId = result.getInt("Contact_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            int contactId = result.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(id, title, description, location, type, start, end, customerId, userId, contactId);
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
                                      String type, LocalDateTime start, LocalDateTime end, int customerId,
                                      int contactId, int userId) throws SQLException {

        String insertStatement = "INSERT appointments(Title, Description, Location, Type, " +
                "Start, End, Customer_ID, Contact_ID, User_ID, Appointment_ID)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, null);";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, contactId);
        ps.setInt(9, userId);

        ps.execute();
    }

    public static int updateAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int contactId, int customerId, int userId, int apptId) throws SQLException {

        String insertStatement = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?," +
                " Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(insertStatement);


        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, contactId);
        ps.setInt(9, userId);
        ps.setInt(10, apptId);


        return ps.executeUpdate();
    }


    public static void updateAppointment(Appointment appointment) {

        String sql = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {


            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            ps.setInt(7, appointment.getCustomerId());
            ps.setInt(8, appointment.getContactId());
            ps.setInt(9, appointment.getUserId());
            ps.setInt(10, appointment.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static ObservableList<ReportObject> getAppointmentByMonthAndType(String selectedMonth, String selectedType) throws SQLException {

        ObservableList<ReportObject> generateMonthTypeReport = FXCollections.observableArrayList();

        String appointment = "SELECT COUNT(*) FROM appointments where type = ? AND monthname(start) = ";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(appointment);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {

            int count = resultSet.getInt("Count");
            String monthname = resultSet.getString("Month");
            String type = resultSet.getString("Type");


            ReportObject reportobject = new ReportObject(count, monthname, type);
            generateMonthTypeReport.add(reportobject);

        }
        return generateMonthTypeReport;
    }
}


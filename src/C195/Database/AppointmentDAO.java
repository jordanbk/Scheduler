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
    /**
     *  Connects to the database and uses SQL statement to select all appointment attributes
     *  create new appointment object and add to observable array list
     * @return
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointments() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        DatabaseConnection.openConnection();
        String sqlStatement = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM appointments ";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sqlStatement);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            int id = result.getInt("Appointment_ID");
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

    /**
     * Connecs to database and uses SQL DELETE statement to remove an Appointment by the Appointment ID
     * @param appointmentId
     * @throws SQLException
     */
    public static void deleteAppointment(int appointmentId) throws SQLException {
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(deleteStatement);
        ps.setInt(1, appointmentId);

        ps.execute();
    }

    /**
     * Connects to database and uses SQL INSERT statement to add an Appointment to
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param contactId
     * @param userId
     * @throws SQLException
     */
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

        String appointment = "SELECT COUNT(*) FROM appointments where type = ? AND monthname(start) = ?";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(appointment);

        ps.setString(1, selectedType);
        ps.setString(2, selectedMonth);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {

            int count = resultSet.getInt(1);

            ReportObject reportobject = new ReportObject(count, selectedMonth, selectedType);
            generateMonthTypeReport.add(reportobject);

        }
        return generateMonthTypeReport;
    }

    public static ObservableList<Appointment> getApptByCustomerId(int customerId) throws SQLException {
        ObservableList<Appointment> generateReportByCustId = FXCollections.observableArrayList();

        String statement = "select * from appointments where Customer_ID = ?;";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(statement);

        ps.setInt(1, customerId);

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {

            int apptId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("End").toLocalDateTime();
            int contactId = resultSet.getInt("Contact_ID");
            int userId = resultSet.getInt("User_ID");
            int custId = resultSet.getInt("Customer_ID");

            Appointment apptObject = new Appointment(apptId, title, description, location, type, start, end, contactId, userId, custId);
            generateReportByCustId.add(apptObject);
        }
        return generateReportByCustId;
    }


    public static ObservableList<Appointment> getApptByContactId(int contactId) throws SQLException {
        ObservableList<Appointment> generateReportByContactId = FXCollections.observableArrayList();

        String statement = "select * from appointments where Contact_ID = ?;";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(statement);

        ps.setInt(1, contactId);

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {

            int apptId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("End").toLocalDateTime();
            int contactID = resultSet.getInt("Contact_ID");
            int userId = resultSet.getInt("User_ID");
            int custId = resultSet.getInt("Customer_ID");

            Appointment apptObject = new Appointment(apptId, title, description, location, type, start, end, contactId, userId, custId);
            generateReportByContactId.add(apptObject);
        }
        return generateReportByContactId;
    }

    public static boolean getOverlappingAppt(LocalDateTime newApptStart, LocalDateTime newApptEnd, int customerID, int id) throws Exception {

        ObservableList<Appointment> appointments = AppointmentDAO.getApptByCustomerId(customerID);

        for (Appointment appt : appointments) {
            if(appt.getId() == id){
                continue;
            }
            (newApptStart >=  && newApptStart <= appt.getEnd()) ||

            //check for overlap (3 conditions) use webinar

        }
        return false;
    }

}


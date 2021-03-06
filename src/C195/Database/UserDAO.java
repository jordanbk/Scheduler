package C195.Database;

import C195.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;


public class UserDAO {

    /**
     *  Retrieve all user details from the database
     * @return all User objects
     * @throws SQLException
     */
    public static ObservableList<User> getAllUsers() throws SQLException {

        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String selectStatement = "SELECT * FROM users";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(selectStatement);
        ps.execute();

        ResultSet rs = ps.getResultSet();
        while (rs.next()){
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            User userComplete = new User(userId, userName, password, null, createdBy, null, lastUpdatedBy);
            allUsers.add(userComplete);
        }
        return allUsers;
    }

}

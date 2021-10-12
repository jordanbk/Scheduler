package C195.Database;

import C195.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class UserDAO {

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
            //Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            //Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            User userComplete = new User(userId, userName, password, null, createdBy, null, lastUpdatedBy);
            allUsers.add(userComplete);
        }


        return allUsers;
    }
    public static User getCurrentUser(Integer userID, Connection connection) throws SQLException {
        User currentUser = new User();
        String query = "SELECT * FROM users WHERE User_ID=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userID);
        ps.execute();
        ResultSet rs = ps.getResultSet();

        if (rs.next()){
            currentUser = userResults(rs);
        }


        return currentUser;
    }

    private static User userResults(ResultSet rs) throws SQLException{
        User user = new User();

        user.setUserId(rs.getInt("User_ID"));
        user.setUserName(rs.getString("User_Name"));
        user.setPassword(rs.getString("Password"));

        return user;
    }

}

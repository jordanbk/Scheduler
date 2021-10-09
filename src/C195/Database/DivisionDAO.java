package C195.Database;

import C195.Model.Division;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DivisionDAO {


    public static ObservableList<Division> getAllDivisions() throws Exception {
        ObservableList <Division> allDivisions = FXCollections.observableArrayList();
        DatabaseConnection.openConnection();
        String statement = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(statement);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("COUNTRY_ID");

            Division divisionResult = new Division(divisionID, divisionName, createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);
            allDivisions.add(divisionResult);

        }
        return allDivisions;
    }
}

package C195.Database;

import C195.Model.Country;
import C195.Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class CountryDAO {

    public static ObservableList<Country> getAllCountries() throws Exception {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();

        DatabaseConnection.openConnection();
        String selectCountry = "SELECT * FROM countries";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(selectCountry);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdated = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            Country countries = new Country(countryID, countryName, createDate, createdBy, lastUpdated, lastUpdatedBy);
            allCountries.add(countries);
        }
        return allCountries;
    }
}

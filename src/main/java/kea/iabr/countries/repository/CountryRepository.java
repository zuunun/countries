package kea.iabr.countries.repository;

import kea.iabr.countries.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM countries";

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Country country = new Country(resultSet.getLong("id"), resultSet.getString("name"));
                countries.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }
}








package edu.fpdual.webservices.model.manager.impl;

import edu.fpdual.webservices.model.conector.MySQLConnector;

import edu.fpdual.webservices.model.manager.SuggestionsManager;

import java.sql.*;

public class SuggestionsManagerImpl implements SuggestionsManager {
    @Override
    public int insertSuggestion(Connection con,String name, String text) throws SQLException {
        //prepare SQL statement
        String sql = "Insert into Suggestions (PLAYER_NAME, suggestion) VALUES (?,?)";


        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setString(1, name);
            stmt.setString(2, text);

            int affectedRows = stmt.executeUpdate();
            if(affectedRows<=0){
                return 0;
            }
            // Queries the DB
            ResultSet resultSet = stmt.getGeneratedKeys();
            // Set before first registry before going through it
            resultSet.beforeFirst();
            resultSet.next();


            // Queries the DB
            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        }
    }
    @Override
    public MySQLConnector getConnector() {
        return new MySQLConnector();
    }
}

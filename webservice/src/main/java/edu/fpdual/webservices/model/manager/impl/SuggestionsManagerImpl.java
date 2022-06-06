package edu.fpdual.webservices.model.manager.impl;

import edu.fpdual.webservices.model.conector.MySQLConnector;

import edu.fpdual.webservices.model.dao.Suggestion;
import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.SuggestionsManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Suggestion findSuggestion (Connection con, Integer id) {
        //prepare SQL statement
        String sql = "select * "
                + "from suggestions "
                + "where idSuggestions = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1,id);

            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            Suggestion sugu = null;
            while(result.next()){
                sugu=(new Suggestion(result));
            }
            return sugu;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Suggestion> ListAll(Connection con) {
        // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery("SELECT * FROM Suggestions");
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<Suggestion> sugus = new ArrayList<>();


            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                sugus.add(new Suggestion(result));

            }

            return sugus;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Connection con, Suggestion sugu) throws SQLException {
        //prepare SQL statement
        String sql = "DELETE from player WHERE idSuggestions = ?";

        // Create general statement
        try (PreparedStatement stmt= con.prepareStatement(sql)){

            //Add Parameters
            stmt.setInt(1, sugu.getIdSuggestion());

            // Queries the DB
            return stmt.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean update(Connection con, Suggestion sugu) throws SQLException {
        //prepare SQL statement
        String sql="Update suggestions set suggestion= ? , player_name=? where id=?";


        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            //Add Parameters
            stmt.setString(1, sugu.getSuggestion());
            stmt.setString(2, sugu.getPlayer_name());
            stmt.setInt(3, sugu.getIdSuggestion());

            // Queries the DB
            return stmt.executeUpdate() > 0;

        }  catch (SQLException e) {
            return false;

        }
    }

    @Override
    public boolean validate(Connection con, Integer id) throws SQLException {
        //prepare SQL statement
        String sql = "select * "
                + "from Suggestion "
                + "where idSuggestions = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            Suggestion sugu = null;

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                sugu = new Suggestion(result);

            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public MySQLConnector getConnector() {
        return new MySQLConnector();
    }
}

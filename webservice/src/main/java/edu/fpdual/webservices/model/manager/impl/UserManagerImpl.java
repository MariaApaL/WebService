package edu.fpdual.webservices.model.manager.impl;

import edu.fpdual.webservices.model.manager.UserManager;

import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.UserManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagerImpl implements UserManager {

    @Override
    public UserDao findByName(Connection con, String player_name) {
        //prepare SQL statement
        String sql = "select * "
                + "from PLAYER "
                + "where PLAYER_NAME = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, player_name);

            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            UserDao user = null;
            while(result.next()){
                user=(new UserDao(result));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Connection con,UserDao user) {
        //prepare SQL statement
        String sql = "Insert into player (PLAYER_NAME, PASSWORD, NUM_GAME , correo) VALUES (?,?, 0,?)";


        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setString(1, user.getPlayer_name());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getMail());
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
    public UserDao findById(Connection con, Integer id) {
        //prepare SQL statement
        String sql = "select * "
                + "from player "
                + "where id = ? ";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            UserDao user = null;

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                user = new UserDao(result);

            }

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Connection con, UserDao user) throws SQLException {
        //prepare SQL statement
        String sql = "DELETE from player WHERE idplayer = ?";

        // Create general statement
        try (PreparedStatement stmt= con.prepareStatement(sql)){

            //Add Parameters
            stmt.setInt(1, user.getIdplayer());

            // Queries the DB
            return stmt.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Connection con, UserDao user) throws SQLException {
        //prepare SQL statement
        String sql="Update player set password= ? , correo=? where id=?";


            // Create general statement
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                //Add Parameters
                stmt.setString(1, user.getPassword());
                stmt.setString(2, user.getMail());
                stmt.setInt(3, user.getIdplayer());

                // Queries the DB
                return stmt.executeUpdate() > 0;

             }  catch (SQLException e) {
                return false;

        }

    }
    @Override
    public boolean validate(Connection con, Integer id) throws SQLException{

        //prepare SQL statement
        String sql = "select * "
                + "from PLAYER "
                + "where idPlayer = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            UserDao user = null;

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                user = new UserDao(result);

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
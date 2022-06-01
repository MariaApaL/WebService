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
    public boolean findUser(Connection con, String player_name) {
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

            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public int insertUser(Connection con, String player_name, String password, String mail) {
        //prepare SQL statement
        String sql = "Insert into player (PLAYER_NAME, PASSWORD, NUM_GAME , correo) VALUES (?,?, 0,?)";


        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setString(1, player_name);
            stmt.setString(2, password);
            stmt.setString(3, mail);
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
    public boolean updateNumGame(Connection con, String name) throws SQLException{

        //prepare SQL statement
        String sql="Update player set num_game= (num_game +1)where player_name=?";

        //prepare SQL statement
        try(PreparedStatement stmt=con.prepareStatement(sql)){

            //Add Parameters
            stmt.setString(1, name);

            // Queries the DB
            return stmt.executeUpdate() > 0;

        }catch(SQLException e){
        return false;}

    }

    @Override
    public boolean deleteUser(Connection con, String name) throws SQLException {
        //prepare SQL statement
        String sql = "DELETE from player WHERE player_name = ?";

        // Create general statement
        try (PreparedStatement stmt= con.prepareStatement(sql)){

            //Add Parameters
            stmt.setString(1, name);

            // Queries the DB
            return stmt.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public List ranking(Connection con) throws SQLException {
        //prepare SQL statement
        String sql = "select player_name  from PLAYER  order  by  num_game desc limit 10";

        // Create general statement
        try(Statement stmt=con.createStatement()){
            // Queries the DB
            ResultSet result = stmt.executeQuery(sql);
            // Set before first registry before going through it
            result.beforeFirst();

            // Initialize variable
            List players = new ArrayList();

            // Run through each result
            while (result.next()) {
               /* int a=1;
               String player=result;
                String aux= String.valueOf(a);
                */


                // Initializes a player per result
                players.add(result.getString(1));

            }

            return players;

        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int numGame(Connection con, String name) throws SQLException {
        //prepare SQL statement
        String sql = "select num_game  from PLAYER  where player_name=?";

        // Create general statement
        try(PreparedStatement stmt=con.prepareStatement(sql)){

            //Add Parameters
            stmt.setString(1, name);

            // Queries the DB
            ResultSet result = stmt.executeQuery(sql);
            // Set before first registry before going through it
            result.beforeFirst();

            // Queries the DB
            return result.getInt(1);

        }catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean updatePassword(Connection con, String contraseña, String name) throws SQLException {
        //prepare SQL statement
        String sql="Update player set password= ?where player_name=?";


            // Create general statement
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                //Add Parameters
                stmt.setString(1, contraseña);
                stmt.setString(2, name);

                // Queries the DB
                return stmt.executeUpdate() > 0;

             }  catch (SQLException e) {
                return false;

        }

    }
    @Override
    public boolean validateUser(Connection con, String password, String name) throws SQLException{

        //prepare SQL statement
        String sql = "select * "
                + "from PLAYER "
                + "where PLAYER_NAME = ? and password=?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, name);
            stmt.setString(2, password);

            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            UserDao user = null;



            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getMail(Connection con, String name) throws SQLException{

        //prepare SQL statement
        String sql = "select correo  from player  where player_name = ?";
        // Create general statement
        try(PreparedStatement stmt=con.prepareStatement(sql)){

            //Add Parameters
            stmt.setString( 1, name);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it
            result.beforeFirst();
            String resultado=null;
            if(result.next()){
                resultado = result.getString(1);
            }
            // Queries the DB
            return resultado;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public MySQLConnector getConnector() {
        return new MySQLConnector();
    }










}
package edu.fpdual.webservices.model.manager.impl;

import edu.fpdual.webservices.model.manager.UserManager;
import edu.fpdual.webservices.model.dao.UserDao;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


public class UserManagerImpl implements UserManager {


    @Override
    public List findAll(Connection con) {
        // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery("SELECT * FROM PLAYER");
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<UserDao> users = new ArrayList<>();


            // Run through each result
            while (result.next()) {
                // Initializes a user per result
                users.add(new UserDao(result));

            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean delete(Connection con, Integer id) {
        //prepare SQL statement
        String sql = "DELETE FROM player WHERE idplayer = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int register(Connection con, UserDao userDao) {
        //prepare SQL statement
        String sql = "INSERT INTO player (idplayer, player_name, password, num_game) values(?,?,?,?)";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setInt(1, userDao.getIdplayer());
            stmt.setString(2, userDao.getPlayer_name());
            stmt.setString(3, userDao.getPassword());
            stmt.setInt(4, userDao.getNum_game());
            // Queries the DB
            int affectedRows = stmt.executeUpdate();

            if(affectedRows<=0){
                return 0;
            }

            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.beforeFirst();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public boolean update(Connection con, UserDao user) {
        //prepare SQL statement
        String sql = "UPDATE player SET player_name=? WHERE player_name = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, user.getPlayer_name());

            // Queries the DB
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserDao> findAllByName(Connection con, Set<String> names) {
        //prepare SQL statement
        String sql = String.format("select player_name"
                        + "from player",
                names.stream().map(data -> "\"" + data + "\"").collect(Collectors.joining(", ")));

        // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery(sql);
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            List<UserDao> users = new ArrayList<>();

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                UserDao user = new UserDao(result);

                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public UserDao findUserByName (Connection con, String player_name) throws SQLException, ClassNotFoundException {
        //prepare SQL statement
        String sql = "select * "
                + "from player "
                + "where player_name= ? ";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, player_name);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            UserDao userDao = null;

            // Run through each result
            while (result.next()) {

                userDao = new UserDao(result);

            }

            return userDao;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    }


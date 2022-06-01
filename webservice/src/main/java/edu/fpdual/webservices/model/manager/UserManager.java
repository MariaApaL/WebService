package edu.fpdual.webservices.model.manager;

import edu.fpdual.webservices.model.conector.MySQLConnector;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserManager {
    /**
     * Validates that the user exist.
     *
     * @param con DB connection
     * @param player_name The entity to check
     * @return a {@link Boolean}
     */
    public boolean findUser(Connection con, String player_name);



    public MySQLConnector getConnector();
    /**
     * Insert a new user on DB
     *
     * @param con DB connection
     * @param player_name The entity to check
     * @param password The entity to create
     * @return a {@link Boolean}
     */
    public int insertUser(Connection con, String player_name, String password, String mail)throws SQLException;
    /**
     * player update num_game on DB
     *
     * @param con DB connection
     * @param name Entities name to use when player plays a new game.
     * @return a {@link Boolean}
     */
    public boolean updateNumGame(Connection con, String name)throws SQLException;
    /**
     * Delete a user using their names.
     *
     * @param con DB connection
     * Using App.getPlayerName to delete.
     * @return a {@link Boolean}
     */
    public boolean deleteUser(Connection con, String name) throws SQLException;
    /**
     * Show the top ten users with the most games played
     *
     * @param con DB connection
     * @return a {@link List}
     */
    public List ranking(Connection con) throws SQLException;
    /**
     * method to know how many games player has played
     *
     * @param con DB connection
     * Using the name who is conected
     * @return a {@link int}
     */
    public int numGame(Connection con, String name)throws SQLException;

    /**
     * Update password on DB.
     *
     * @param con DB connection
     * @param contraseña Entities password to delete.
     * @return a {@link Boolean}
     */
    public boolean updatePassword(Connection con, String contraseña, String name) throws SQLException;
    /**
     * Validate that the password is correct.
     *
     * @param con DB connection
     * @param password Entities id to delete.
     * @return a {@link Boolean}
     */
    public boolean validateUser(Connection con, String password, String name) throws SQLException;

    /**
     * Get mail.
     *
     * @param con DB connection
     * @return a {@link String}
     */
    public String getMail(Connection con, String name) throws SQLException;

}


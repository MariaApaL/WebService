package edu.fpdual.webservices.model.manager;

import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.UserDao;


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
    public UserDao findUser(Connection con, String player_name);



    public MySQLConnector getConnector();
    /**
     * Insert a new user on DB
     *
     * @param con DB connection

     * @return a {@link Boolean}
     */
    public int insertUser(Connection con, UserDao user)throws SQLException;
    /**
     * find a player by id (idPlayer) on DB
     *
     * @param con DB connection
     * @param id Entities id to use when player plays a new game.
     * @return a {@link UserDao}
     */
    public UserDao findById(Connection con, Integer id)  throws SQLException, ClassNotFoundException;

    /**
     * Delete a user using their names.
     *
     * @param con DB connection
     * Using App.getPlayerName to delete.
     * @return a {@link Boolean}
     */
    public boolean deleteUser(Connection con, UserDao user) throws SQLException;
    /**
     * update users
     *
     * @param con DB connection
     *
     * @return a {@link boolean}
     */

    public boolean update(Connection con, UserDao user) throws SQLException;
    /**
     * Validate that the password is correct.
     *
     * @param con DB connection
     * @param id Entities id to delete.
     * @return a {@link Boolean}
     */
    public boolean validateUser(Connection con, Integer id) throws SQLException;

}


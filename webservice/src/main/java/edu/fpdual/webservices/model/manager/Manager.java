package edu.fpdual.webservices.model.manager;


import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Manager<T, U> {


     MySQLConnector getConnector();
    /**
     * Insert a new user on DB
     *
     * @param con DB connection

     * @return a {@link Boolean}
     */
     int insert(Connection con, T entity)throws SQLException;
    /**
     * find a player by id (idPlayer) on DB
     *
     * @param con DB connection
     * @param id Entities id to use when player plays a new game.
     * @return a {@link T}
     */
     T findById(Connection con, U id)  throws SQLException, ClassNotFoundException;

    /**
     * Delete a user using their names.
     *
     * @param con DB connection
     * Using App.getPlayerName to delete.
     * @return a {@link Boolean}
     */
     boolean delete(Connection con, T entity) throws SQLException;
    /**
     * update users
     *
     * @param con DB connection
     *
     * @return a {@link boolean}
     */

     boolean update(Connection con, T entity) throws SQLException;
    /**
     * Validate that the password is correct.
     *
     * @param con DB connection
     * @param id Entities id to delete.
     * @return a {@link Boolean}
     */
     boolean validate(Connection con, U id) throws SQLException;

}
